package ma.learn.quiz.rest.etudiant;

import freemarker.template.TemplateException;
import ma.learn.quiz.bean.User;
import ma.learn.quiz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/etudiant/user")
public class EtudiantUser {

    @PostMapping("/login")
    public ResponseEntity<User> signIn(@RequestBody User user) {
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        return userService.signIn(user);
    }

    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/")
    public User save(@RequestBody User user) throws MessagingException, IOException, TemplateException {
        return userService.save(user);
    }

    @Autowired
    private UserServiceImpl userService;
}
