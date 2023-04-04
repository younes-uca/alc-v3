package ma.learn.quiz.rest.user;

import freemarker.template.TemplateException;
import ma.learn.quiz.bean.User;
import ma.learn.quiz.exception.NotAnImageFileException;
import ma.learn.quiz.service.impl.AdminService;
import ma.learn.quiz.service.impl.EtudiantService;
import ma.learn.quiz.service.impl.ProfService;
import ma.learn.quiz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static ma.learn.quiz.filter.JwtConstant.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping("/user")
public class user {

    @PostMapping("/login")
    public ResponseEntity<User> signIn(@RequestBody User user) {
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        return userService.signIn(user);
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user) {
        System.out.println(user.getId());
        if (user.getId() == null) {
            return null;
        } else {
            return userService.updateUser(user);
        }
    }

    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/")
    public User save(@RequestBody User user) throws MessagingException, IOException, TemplateException {
        return userService.save(user);
    }

    @GetMapping(path = "/image/profile/{username}", produces = IMAGE_JPEG_VALUE)
    public byte[] getTempProfileImage(@PathVariable("username") String username) throws IOException {
        URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL + username);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (InputStream inputStream = url.openStream()) {
            int bytesRead;
            byte[] chunk = new byte[1024];
            while ((bytesRead = inputStream.read(chunk)) > 0) {
                byteArrayOutputStream.write(chunk, 0, bytesRead);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @PostMapping("/updateProfileImage")
    public ResponseEntity<User> updateProfileImage(@RequestParam("username") String username, @RequestParam(value = "profileImage") MultipartFile profileImage)
            throws IOException, NotAnImageFileException {
        User user = userService.updateProfileImage(username, profileImage);
        return new ResponseEntity<>(user, OK);
    }

    @GetMapping(path = "/image/{username}/{fileName}", produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(USER_FOLDER + username + FORWARD_SLASH + fileName));
    }


    @DeleteMapping("/delete/id/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/resetpassword/username/{username}")
    public String resetPassword(@PathVariable String username) throws MessagingException, IOException, TemplateException {
        return userService.resetPassword(username);
    }


    @Autowired
    private UserServiceImpl userService;

}
