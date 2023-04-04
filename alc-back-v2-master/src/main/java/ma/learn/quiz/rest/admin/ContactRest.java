package ma.learn.quiz.rest.admin;

import freemarker.template.TemplateException;
import ma.learn.quiz.bean.Contact;
import ma.learn.quiz.service.impl.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/contact")
public class ContactRest {

    @Autowired
    private ContactService contactService;

    @GetMapping("/email/{email}")
    public List<Contact> findByEmail(@PathVariable String email) {
        return contactService.findByEmail(email);
    }

    @GetMapping("/")
    public List<Contact> findAll() {
        return contactService.findAll();
    }

    @PostMapping("/")
    public Contact save(@RequestBody Contact entity) {
        return contactService.save(entity);
    }

    @PostMapping("/reply")
    public Contact reply(@RequestBody Contact entity) throws MessagingException, TemplateException, IOException {
        return contactService.reply(entity);
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id) {
        contactService.deleteById(id);
    }
}
