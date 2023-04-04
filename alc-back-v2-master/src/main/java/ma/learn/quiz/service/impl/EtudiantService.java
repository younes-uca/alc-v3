package ma.learn.quiz.service.impl;

import freemarker.template.TemplateException;
import ma.learn.quiz.bean.*;
import ma.learn.quiz.configuration.EmailSenderService;
import ma.learn.quiz.configuration.MailComponent;
import ma.learn.quiz.dao.*;
import ma.learn.quiz.exception.ObjectNotFoundException;
import ma.learn.quiz.service.facade.UserService;
import ma.learn.quiz.service.vo.EtudiantVo;
import ma.learn.quiz.vo.UserCodeValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static ma.learn.quiz.filter.RoleConstant.ROLE_STUDENT;
import static org.springframework.http.HttpStatus.OK;

@Service
public class EtudiantService extends AbstractService {

    @Autowired
    private PackStudentService packStudentService;
    @Autowired
    public SessionCoursService sessionCoursService;
    @Autowired
    public InscriptionDao inscriptionDao;
    @Autowired
    public EtatInscriptionService etatInscriptionService;
    @Autowired
    public NiveauEtudeDao niveauEtudeDao;
    @Autowired
    public InteretEtudiantDao interetEtudiantDao;
    @Autowired
    public FonctionDao fonctionDao;
    @Autowired
    public StatutSocialDao statutSocialDao;
    @Autowired
    public SkillDao skillDao;
    @Autowired
    public EtudiantDao etudiantDao;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    public CentreService centreService;
    @Autowired
    public ParcoursService parcoursService;
    @Autowired
    public ProfService profService;
    @Autowired
    public ScheduleProfDao scheduleProfDao;
    @Autowired
    public EntityManager entityManager;
    @Autowired
    private UserService userService;
    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private GroupeEtudiantDetailService groupeEtudiantDetailService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private GroupeEtudiantService groupeEtudiantService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;


    public List<Etudiant> findByParcoursCode(String code) {
        return etudiantDao.findByParcoursCode(code);
    }

    public Etudiant findByLogin(String login) {
        return etudiantDao.findByUsername(login);
    }

    public Etudiant update(Etudiant etudiant) {
        Etudiant loadedEtudiant = findEtudiantById(etudiant.getId());
        Parcours parcours = parcoursService.findParcoursById(etudiant.getParcours().getId());
        loadedEtudiant.setParcours(parcours);
        loadedEtudiant.setNom(etudiant.getNom());
        loadedEtudiant.setPrenom(etudiant.getPrenom());
        loadedEtudiant.setUsername(etudiant.getUsername());
        loadedEtudiant.setInteretEtudiant(etudiant.getInteretEtudiant());
        loadedEtudiant.setNiveauEtude(etudiant.getNiveauEtude());
        loadedEtudiant.setFonction(etudiant.getFonction());
        loadedEtudiant.setStatutSocial(etudiant.getStatutSocial());
        loadedEtudiant.setSkill(etudiant.getSkill());
        return etudiantDao.save(loadedEtudiant);
    }

    public Etudiant findEtudiantById(Long id) {
        return etudiantDao.findEtudiantById(id);
    }


    public Prof findProfById(Long id) {
        return profService.findProfById(id);
    }

    public int deleteByParcoursCode(String code) {
        return etudiantDao.deleteByParcoursCode(code);
    }

    public List<Etudiant> findByCriteria(EtudiantVo etudiantVo) {
        String query = "SELECT e FROM Etudiant e WHERE 1=1";
        if (etudiantVo.getNom() != null) {
            query += " AND  e.nom LIKE '%" + etudiantVo.getNom() + "%'";
        }
        if (etudiantVo.getPrenom() != null) {
            query += "  AND  e.prenom LIKE '%" + etudiantVo.getPrenom() + "'";
        }

        if (etudiantVo.getLogin() != null) {
            query += "  AND  e.login LIKE '%" + etudiantVo.getLogin() + "'";
        }

        return entityManager.createQuery(query).getResultList();
    }

    public Etudiant findByRef(String ref) {
        return etudiantDao.findByRef(ref);
    }

    public Etudiant findByNom(String nom) {
        return etudiantDao.findByNom(nom);
    }

    public Etudiant findById(Long id) {
        Optional<Etudiant> etudiant = etudiantDao.findById(id);
        return etudiant.orElse(null);
    }


    public User create(Long pack, Etudiant etudiant) throws Exception {
        Etudiant etudiant1 = this.findByLogin(etudiant.getUsername());
        PackStudent packStudent = packStudentService.findById(pack);
        User user;
        EtatInscription etatInscription = etatInscriptionService.findEtatInscriptionById((long) 1);
        if (etudiant1 != null) {
            Inscription inscription = createInscription(etudiant1, packStudent, packStudent.getLevel(), etatInscription, etudiant1);
            inscriptionService.save(inscription);
            return etudiant1;
        } else {
            etudiant.setParcours(packStudent.getLevel());
            String password = this.userService.generatePassword();
            System.out.println("password = " + password);
            System.out.println("email = " + etudiant.getUsername());
            etudiant.setPassword(password);
            etudiant.setAuthorities(Arrays.asList(new Role(ROLE_STUDENT)));
            etudiant.setRole(ROLE_STUDENT);
            etudiant.setNiveauEtude(niveauEtudeDao.findByCode(""));
            etudiant.setInteretEtudiant(interetEtudiantDao.findByCode(""));
            etudiant.setFonction(fonctionDao.findByCode(""));
            etudiant.setStatutSocial(statutSocialDao.findByCode(""));
            etudiant.setSkill(skillDao.findByCode(""));
            user = userService.saveWithPack(etudiant);
            Etudiant etudiant2 = (Etudiant) user;
            Inscription inscription = createInscription(etudiant, packStudent, packStudent.getLevel(), etatInscription, etudiant2);
            inscriptionService.save(inscription);
            return user;
        }
    }

    private Inscription createInscription(Etudiant etudiant, PackStudent packStudent, Parcours parcours, EtatInscription etatInscription, Etudiant etudiant2) {
        Inscription inscription = inscriptionService.findInscriptionByEtudiantId(etudiant.getId());
        if (inscription == null) {
            inscription = new Inscription();
        }
        inscription.setEtudiant(etudiant2);
        inscription.setEtatInscription(etatInscription);
        inscription.setGroupeEtude(etudiant.getGroupeEtude());
        if (parcours != null) {
            inscription.setParcours(parcours);
        }
        if (etudiant.getGroupeEtude() != null) {
            inscription.setGroupeEtude(etudiant.getGroupeEtude());
        }
        if (packStudent != null) {
            inscription.setPackStudent(packStudent);
            inscription.setParcours(packStudent.getLevel());
            inscription.setQuizFinished(true);
            packStudent.setTotalStudents(packStudent.getTotalStudents() + 1);
            packStudentService.update(packStudent);
        }

        return inscription;
    }

    public User create(Etudiant etudiant) throws Exception {
        User userRequest;
        Etudiant etudiant1 = this.findByLogin(etudiant.getUsername());
        if (etudiant1 != null) {
            throw new Exception("البريد الالكتروني موجود بالفعل، من فضلك تفقد بريدك الالكتروني للحصول على اسم المستخدم و كلمة المرور للولوج الى حسابك");
        } else {
            userRequest = addNewStudent(etudiant);
            this.userServiceImpl.sentValidateTemplateForStudent(userRequest);
        }
        return userRequest;
    }

    private User addNewStudent(Etudiant etudiant) throws Exception {
        Inscription inscription = new Inscription();
        assert etudiant.getParcours() != null;
        Parcours parcours = parcoursService.findParcoursById(etudiant.getParcours().getId());
        etudiant.setParcours(parcours);

        EtatInscription etatInscription = etatInscriptionService.findEtatInscriptionById((long) 1);

        assert etudiant.getGroupeEtude() != null;
        inscription.setGroupeEtude(etudiant.getGroupeEtude());


        if (parcours != null) {
            inscription.setParcours(etudiant.getParcours());
        }
        String password = this.userService.generatePassword();
        etudiant.setPassword(password);
        etudiant.setAuthorities(Arrays.asList(new Role(ROLE_STUDENT)));
        etudiant.setRole(ROLE_STUDENT);
        assert etudiant.getGroupeEtude() != null;
        inscription.setGroupeEtude(etudiant.getGroupeEtude());
        etudiant.setNiveauEtude(niveauEtudeDao.findByCode(""));
        etudiant.setInteretEtudiant(interetEtudiantDao.findByCode(""));
        etudiant.setFonction(fonctionDao.findByCode(""));
        etudiant.setStatutSocial(statutSocialDao.findByCode(""));
        etudiant.setSkill(skillDao.findByCode(""));
        etudiant.setEnabled(false);
        User user = userService.save(etudiant);
        Etudiant etudiant2 = (Etudiant) user;
        inscription.setEtudiant(etudiant2);
        inscription.setEtatInscription(etatInscription);
        inscriptionService.save(inscription);
        return user;
    }


    public int save(Etudiant etudiant) {
        Parcours parcours = parcoursService.findParcoursById(etudiant.getParcours().getId());
        if (parcours == null) {
            return -3;
        } else {
            etudiant.setParcours(parcours);
            etudiantDao.save(etudiant);
            return 1;
        }
    }

    public Etudiant updateEtudiant(Etudiant etudiant) {
        etudiant.setNiveauEtude(etudiant.getNiveauEtude());
        etudiant.setFonction(etudiant.getFonction());
        etudiant.setInteretEtudiant(etudiant.getInteretEtudiant());
        etudiant.setStatutSocial(etudiant.getStatutSocial());
        etudiant.setSkill(etudiant.getSkill());
        return this.etudiantDao.save(etudiant);
    }

    public int updatePassword(String username, String newPassword) {
        User user = userServiceImpl.loadUserByUsername(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        userDao.save(user);
        return 1;
    }


    public List<Etudiant> findAll() {
        return etudiantDao.findAll();
    }

    @Transactional
    public int deleteEtudiantById(List<Etudiant> etudiant) {
        int res = 0;
        for (int i = 0; i < etudiant.size(); i++) {
            res += deleteEtudiantById(etudiant.get(i).getId());
        }
        return res;
    }

    @Transactional
    public int deleteEtudiantById(Long id) {
        this.inscriptionService.deleteInscriptionByEtudiantId(id);
        List<GroupeEtudiantDetail> detailList = this.groupeEtudiantDetailService.findByEtudiantId(id);
        for (GroupeEtudiantDetail groupeDetail : detailList) {
            GroupeEtudiant groupeEtudiant = this.groupeEtudiantService.findGroupeEtudiantById(groupeDetail.getGroupeEtudiant().getId());
            groupeEtudiant.setNombrePlaceNonVide(groupeEtudiant.getNombrePlaceNonVide() - 1);
            groupeEtudiant.setNombrePlacevide(groupeEtudiant.getNombrePlacevide() + 1);
            this.groupeEtudiantService.update(groupeEtudiant);
        }
        this.groupeEtudiantDetailService.deleteGroupeEtudiantDetailByEtudiantId(id);
        this.dictionaryService.deleteDictionaryByEtudiantId(id);
        return etudiantDao.deleteEtudiantById(id);
    }

    public int deleteByParcoursId(Long id) {
        return etudiantDao.deleteByParcoursId(id);
    }

    public Object findByCritere(String login, String password) {
        String query = "SELECT item FROM Etudiant item WHERE 1=1";
        if (!containsMalicous(login)) {
            query += addCriteria("username", login);
            query += addCriteria("password", password);
            return entityManager.createQuery(query).getSingleResult();
        } else return null;

    }


    public List<Etudiant> findByParcoursLibelle(String libelle) {
        return etudiantDao.findByParcoursLibelle(libelle);
    }

    public List<Etudiant> findByCriteria(Etudiant etudiant) {
        String query = this.init("Etudiant");
        if (etudiant != null) {
            if (etudiant.getNom() != null) {
                query += this.addCriteria("nom", etudiant.getNom(), "LIKE");
            }
            if (etudiant.getPrenom() != null) {
                query += this.addCriteria("prenom", etudiant.getPrenom(), "LIKE");
            }

        }
        System.out.println("query = " + query);
        System.out.println(entityManager.createQuery(query).getResultList().size());
        return entityManager.createQuery(query).getResultList();
    }


    public Etudiant validateToken(String token) throws Exception {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
        LocalDateTime localDateTime = LocalDateTime.now();
        if (confirmationToken.getExpiresAt().isBefore(localDateTime)) {
            throw new Exception("token expired");
        } else {
            confirmationToken.setConfirmedAt(localDateTime);
            confirmationTokenService.save(confirmationToken);
            Etudiant userFromToken = confirmationToken.getUser();
            userFromToken.setEnabled(true);
            return etudiantDao.save(userFromToken);
        }
    }

    public long getNumberOfStudents() {
        return etudiantDao.count();
    }

    public ResponseEntity<Etudiant> startLevelTest(Etudiant etudiant) {
        Etudiant etudiant1 = findByLogin(etudiant.getUsername());
        if (etudiant1 == null) {
            try {
                etudiant1 = (Etudiant) addNewStudent(etudiant);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Random random = new Random();
            int randomNumber = random.nextInt(90000000) + 10000000;
            UserCodeValidate codeValidate = new UserCodeValidate(randomNumber, etudiant1);
            assert etudiant1 != null;
            codes.put(etudiant1.getId(), codeValidate);
            sentCodeToStudent(etudiant1, randomNumber);
            return new ResponseEntity<>(etudiant1, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(etudiant1, OK);
    }

    public void sentCodeToStudent(Etudiant user, int code) {
        MailComponent mailComponent = new MailComponent();

        mailComponent.setTo(user.getUsername());
        mailComponent.setSubject(user.getNom() + ", Welcome to EngFlexy. Please verify your Email address");
        mailComponent.setContent(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "  <head>" +
                        "    <title>" + "  المرجو تاأكيد حسابك من خلال الرمز أسفله" + user.getNom() + "</title>" +
                        "  </head>" +
                        "  <body   align=\"right\" >" +
                        " <h1> " + "  المرجو تاأكيد حسابك من خلال الرمز أسفله " + user.getNom() + " </h1> " +
                        " <h3> " + code + " رمز تأكيد حسابك هو  " + " </h3> " +
                        " <div> " +
                        "<p style=\"color:red; font-weight:bold \">   مدة صلاحية الرمز هي 24 ساعة</p>" +
                        " <div> " +
                        "  </body>" +
                        "</html>"
        );

        try {
            this.emailSenderService.sent(mailComponent);
        } catch (MessagingException | TemplateException | IOException e) {
            e.printStackTrace();
        }
    }

    public int verifyEmail(Etudiant student, int code) throws Exception {
        System.out.println(student.getId());
        System.out.println(code);
        UserCodeValidate codeValidate = codes.get(student.getId());
        System.out.println(codeValidate);
        if (codeValidate == null) {
            throw new ObjectNotFoundException("Your account not found !");
        }
        if (code == codeValidate.getRandomNumber() && timeIsNotExpired(codeValidate.getDate())) {
            codes.remove(student.getId());
            return 1;
        }
        return 0;
    }

    private boolean timeIsNotExpired(LocalDate date) {
        LocalDate now = LocalDate.now();
        return (date.compareTo(now) >= 0);
    }

    Map<Long, UserCodeValidate> codes = new HashMap<>();
}
