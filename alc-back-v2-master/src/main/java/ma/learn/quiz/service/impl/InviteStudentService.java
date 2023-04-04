package ma.learn.quiz.service.impl;

import freemarker.template.TemplateException;
import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.bean.InviteStudent;
import ma.learn.quiz.bean.User;
import ma.learn.quiz.configuration.EmailSenderService;
import ma.learn.quiz.configuration.MailComponent;
import ma.learn.quiz.dao.InviteStudentdDao;
import ma.learn.quiz.dao.UserDao;
import ma.learn.quiz.service.facade.UserService;
import ma.learn.quiz.service.vo.InviteStudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static ma.learn.quiz.service.Util.UtilString.generateStringNumber;

@Service
public class InviteStudentService extends AbstractService {
    @Autowired
    public EmailSenderService mailSender;
    @Autowired
    private InviteStudentdDao inviteStudentdDao;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    public EntityManager entityManager;
    @Value("${emailling.contact.form}")
    private String from;

    public List<InviteStudent> findInviteStudentByEtudiantId(Long id) {
        return inviteStudentdDao.findInviteStudentByEtudiantId(id);
    }

    public InviteStudent findInviteStudentByEmailInvited(String emailInvited) {
        return inviteStudentdDao.findInviteStudentByEmailInvited(emailInvited);
    }

    public InviteStudent findInviteStudentByEmailInvitedAndCode(String emailInvited, String code) {
        return inviteStudentdDao.findInviteStudentByEmailInvitedAndCode(emailInvited, code);
    }

    public List<InviteStudent> findAll() {
        return inviteStudentdDao.findAll();
    }

    public List<InviteStudent> findAllByCriteria(InviteStudentVo inviteStudentVo) {
        String query = this.init("InviteStudent");

        if (inviteStudentVo.getCode() != null) {
            query += this.addCriteria("code", inviteStudentVo.getCode(), "LIKE");
        }
        if (inviteStudentVo.getEtudiant() != null) {
            query += this.addCriteria("etudiant.nom", inviteStudentVo.getEtudiant(), "LIKE");
        }
        if (inviteStudentVo.getEmailInvited() != null) {
            query += this.addCriteria("emailInvited", inviteStudentVo.getEmailInvited(), "LIKE");
        }
        if (inviteStudentVo.getDateSentInvitation() != null) {
            query += this.addCriteria("dateSentInvitation", inviteStudentVo.getDateSentInvitation(), "LIKE");
        }

        if (inviteStudentVo.getIsAccepted() != null) {
            query += this.addCriteria("isAccepted", inviteStudentVo.getIsAccepted());
        }
        if (inviteStudentVo.getIsPaidPack() != null) {
            query += this.addCriteria("isPaidPack", inviteStudentVo.getIsPaidPack());
        }


        System.out.println("query = " + query);
        return entityManager.createQuery(query).getResultList();
    }

    public int saveInvitation(Long idStudent, String emailInvited) throws MessagingException, TemplateException, IOException {
        Etudiant etudiant = etudiantService.findEtudiantById(idStudent);
        InviteStudent inviteStudent2 = findInviteStudentByEmailInvited(emailInvited);
        User userInvited = userDao.findByUsername(emailInvited);
        if (etudiant == null || inviteStudent2 != null) {
            return -1;
        } else {
            if (userInvited != null) {
                return -2;
            } else {
                InviteStudent inviteStudent = new InviteStudent();
                inviteStudent.setDateSentInvitation(new Date());
                inviteStudent.setAccepted(false);
                inviteStudent.setCode(generateStringNumber(8));
                inviteStudent.setPaidPack(false);
                inviteStudent.setEmailInvited(emailInvited);
                inviteStudent.setEtudiant(etudiant);
                inviteStudentdDao.save(inviteStudent);
                this.sentMessageToInvited(etudiant.getUsername(), emailInvited, inviteStudent.getCode());
                this.sentMessageToUser(etudiant.getUsername());
                return 1;
            }
        }
    }

    public void sentMessageToInvited(String email, String emailInvited, String code) throws MessagingException, TemplateException, IOException {
        System.out.println("prepare email ");
        SimpleMailMessage message = new SimpleMailMessage();
        MailComponent mailComponent = new MailComponent();
        mailComponent.setTo(emailInvited);
        mailComponent.setFrom(from);
        mailComponent.setSubject("Invited to the platform engflexy");
        mailComponent.setContent("Hey there welcome to our platform e-learning,you are invited by " + email + " .\n" +
                "Click here : https://engflexy.com/public/connectAsInvited" + "\n" +
                "Your invitation code is:" + code + "\n" +
                "You will get 3 free courses for your first purchase.");
        mailSender.sent(mailComponent);
        System.out.println("email send");
    }

    public void sentMessageToUser(String email) throws MessagingException, TemplateException, IOException {
        System.out.println("prepare email ");
        MailComponent message = new MailComponent();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Invitation sent successfully");
        message.setContent("Hey there thank you for inviting this new student, you will get promotion for the next pay pack.");
        mailSender.sent(message);
        System.out.println("email send");
    }
}
