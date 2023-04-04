package ma.learn.quiz.service.facade;

import freemarker.template.TemplateException;
import ma.learn.quiz.bean.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService extends UserDetailsService {
    ResponseEntity<User> signIn(User user);

    User save(User user) throws MessagingException, IOException, TemplateException;
    User saveWithPack(User user) throws MessagingException, IOException, TemplateException;
   List<User> findAll();
   String generatePassword();
   void deleteUserById(Long id);
    User updateUser(User user);
}
