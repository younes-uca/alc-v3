package ma.learn.quiz.rest.admin;

import freemarker.template.TemplateException;
import ma.learn.quiz.bean.Prof;
import ma.learn.quiz.bean.User;
import ma.learn.quiz.service.impl.ProfService;
import ma.learn.quiz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUser {

    @PostMapping("/login")
    public ResponseEntity<User> signIn(@RequestBody User user) {
        return userService.signIn(user);
    }

    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/")
    public User save(@RequestBody  User user) throws MessagingException, IOException, TemplateException {
        return userService.save(user);
    }


    @PostMapping("/allow")
    public Prof allowUser(@RequestBody Prof prof) {
        return profService.allowTeacher(prof);
    }

    @PostMapping("/lock")
    public Prof lockUser(@RequestBody Prof prof) throws MessagingException, TemplateException, IOException {
        return profService.lockTeacher(prof);
    }

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ProfService profService;
}
