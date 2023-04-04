package ma.learn.quiz.service.impl;

import freemarker.template.TemplateException;
import ma.learn.quiz.bean.Contact;
import ma.learn.quiz.configuration.EmailSenderService;
import ma.learn.quiz.configuration.MailComponent;
import ma.learn.quiz.dao.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactDao contactDao;
    @Autowired
    private EmailSenderService mailSender;

    @Value("${emailling.hello.form}")
    private String from;

    public List<Contact> findByEmail(String email) {
        return contactDao.findByEmail(email);
    }

    public List<Contact> findAll() {
        return contactDao.findAll();
    }

    public Contact save(Contact entity) {
        return contactDao.save(entity);
    }

    public Contact reply(Contact entity) throws MessagingException, TemplateException, IOException {
        MailComponent mailComponent = new MailComponent();

        mailComponent.setTo(entity.getEmail());
        mailComponent.setFrom(from);
        mailComponent.setSubject("Hello " + entity.getName());
        mailComponent.setContent(entity.getMessage());
        mailSender.sent(mailComponent);
        return contactDao.save(entity);
    }

    @Transactional
    public void deleteById(Long aLong) {
        contactDao.deleteById(aLong);
    }
}
