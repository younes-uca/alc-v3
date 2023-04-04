package ma.learn.quiz.service.impl;


import freemarker.template.TemplateException;
import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.bean.User;
import ma.learn.quiz.configuration.CallConfiguration;
import ma.learn.quiz.configuration.ConstantFileNames;
import ma.learn.quiz.configuration.EmailSenderService;
import ma.learn.quiz.configuration.MailComponent;
import ma.learn.quiz.dao.UserDao;
import ma.learn.quiz.exception.NotAnImageFileException;
import ma.learn.quiz.service.facade.RoleService;
import ma.learn.quiz.service.facade.UserService;
import ma.learn.quiz.util.JwtUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static ma.learn.quiz.filter.JwtConstant.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.*;

@Service
public class UserServiceImpl implements UserService {

    public Map<Long, User> connectedStudent = new HashMap<>();
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    private CallConfiguration callConfiguration;
    @Value("${emailling.info.form}")
    private String from;


    @Override
    public ResponseEntity<User> signIn(User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("bad credited for username " + user.getUsername());
        }
        User loadUserByUsername = loadUserByUsername(user.getUsername());

        HttpHeaders jwtHeader = getJwtHeader(loadUserByUsername);
        loadUserByUsername.setToken(jwtUtil.generateToken(loadUserByUsername));
        if (loadUserByUsername.getRole().equals("STUDENT")) {

            this.connectedStudent.put(loadUserByUsername.getId(), loadUserByUsername);
        }
        return new ResponseEntity<>(loadUserByUsername, jwtHeader, OK);
    }

    public User allowUser(User user) {
        return userDao.save(user);
    }

    HttpHeaders getJwtHeader(User user) {
        String token = jwtUtil.generateToken(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, token);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        return headers;
    }

    @Override
    public User save(User user) {
        User loadedUser = userDao.findByUsername(user.getUsername());
        if (loadedUser != null)
            return loadedUser;
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setImage(getTemporaryProfileImageUrl(user.getUsername()));
            roleService.save(user.getAuthorities());
            User userRequest = userDao.save(user);
            return userRequest;
        }
    }

    public void sentValidateTemplateForStudent(User userRequest) {
        // add new confirmation token
        String token = confirmationTokenService.addNewConfirmationToken((Etudiant) userRequest);
        String link = callConfiguration.getConfigurationByName("primary.link.for.validate.account") + userRequest.getId() + "/"
                + token;
        MailComponent mailComponent = new MailComponent();
        mailComponent.setPassword(userRequest.getPassword());
        mailComponent.setUsername(userRequest.getUsername());
        mailComponent.setTo(userRequest.getUsername());
        mailComponent.setLink(link);
        mailComponent.setSubject("Hi " + userRequest.getNom() + " please confirm your account on EngFlexy.");
        mailComponent.setFrom(from);
        try {
            this.emailSenderService.sentJavaMail(mailComponent, ConstantFileNames.STUDENT_CONFIRMATION_TEMPLATE_MAIL);
        } catch (MessagingException | TemplateException | IOException e) {
            System.out.println("Message" + e.getMessage());
            e.printStackTrace();
            this.deleteUserById(userRequest.getId());
        }
    }

    @Override
    public User saveWithPack(User user) throws MessagingException, IOException, TemplateException {
        User loadedUser = userDao.findByUsername(user.getUsername());
        if (loadedUser != null)
            return loadedUser;
        else {
            user.setImage(getTemporaryProfileImageUrl(user.getUsername()));
            MailComponent mailComponent = new MailComponent();
            mailComponent.setPassword(user.getPassword());
            mailComponent.setUsername(user.getUsername());
            mailComponent.setTo(user.getUsername());
            mailComponent.setSubject("Hi " + user.getNom() + " your account on EngFlexy is validated.");
            mailComponent.setFrom(from);
            this.emailSenderService.sentJavaMail(mailComponent, ConstantFileNames.CONFIRMATION_TEMPLATE_MAIL);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            roleService.save(user.getAuthorities());
            return userDao.save(user);
        }
    }


    private String setProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PATH + username +
                FORWARD_SLASH + username + DOT + JPG_EXTENSION).toUriString();
    }


    String getTemporaryProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username)
                .toUriString();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null || user.getId() == null) {
            throw new UsernameNotFoundException("user " + username + " not founded");
        } else {
            return user;
        }
    }


    private void saveProfileImage(User user, MultipartFile profileImage) throws IOException, NotAnImageFileException {

        System.out.println(user.getUsername());
        if (profileImage != null) {
            if (!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains(profileImage.getContentType())) {
                throw new NotAnImageFileException(profileImage.getOriginalFilename() + NOT_AN_IMAGE_FILE);
            }
            Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
                LOGGER.info(DIRECTORY_CREATED + userFolder);
            }
            Files.deleteIfExists(Paths.get(userFolder + user.getUsername() + DOT + JPG_EXTENSION));
            Files.copy(profileImage.getInputStream(), userFolder.resolve(user.getUsername() + DOT + JPG_EXTENSION), REPLACE_EXISTING);
            user.setImage(setProfileImageUrl(user.getUsername()));
            this.userDao.save(user);
            LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM + profileImage.getOriginalFilename());
        }
    }

    @Override
    public String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        System.out.println(user.getId());
        User localUser = loadUserByUsername(user.getUsername());
        localUser.setAddresse(user.getAddresse());
        localUser.setAge(user.getAge());
        localUser.setNom(user.getNom());
        localUser.setNumero(user.getNumero());
        localUser.setPrenom(user.getPrenom());
        localUser.setVille(user.getVille());
        localUser.setSkype(user.getSkype());
        return userDao.save(localUser);
    }

    public User updateProfileImage(String username, MultipartFile profileImage) throws IOException, NotAnImageFileException {
        User user = this.loadUserByUsername(username);
        saveProfileImage(user, profileImage);
        return user;
    }


    public String resetPassword(String username) throws MessagingException, IOException, TemplateException {
        User user = this.loadUserByUsername(username);
        if (user == null) {
            throw new MessagingException("User not found !");
        } else {
            String password = this.generatePassword();
            user.setPassword(password);
            System.out.println(user.getPassword());
            System.out.println(user.getUsername());
            MailComponent mailComponent = new MailComponent();

            mailComponent.setFrom(this.from);
            mailComponent.setTo(user.getUsername());
            mailComponent.setSubject(" لقد تم اعادة تعيين كلمة المرور الخاصة بك");
            mailComponent.setContent(
                    "<!DOCTYPE html>" +
                            "<html>" +
                            "  <head>" +
                            "    <title>" + "  لقد تم اعادة تعيين كلمة المرور الخاصة بك بنجاح." + user.getNom() + "</title>" +
                            "  </head>" +
                            "  <body   align=\"right\" >" +
                            " <h1> " + "  لقد تم اعادة تعيين كلمة المرور الخاصة بك بنجاح " + user.getNom() + " </h1> " +
                            " <h3> " + password + " كلمة المرور الخاصة بك هي " + " </h3> " +
                            "  </body>" +
                            "</html>"
            );

            this.emailSenderService.sent(mailComponent);
            user.setPassword(passwordEncoder.encode(password));
            userDao.save(user);
            return password;
        }
    }

    public Optional<User> findById(Long id) {
        return this.userDao.findById(id);
    }

}
