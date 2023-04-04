package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.User;
import ma.learn.quiz.service.impl.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/chat")
public class ChatRest {

    @GetMapping("/users/")
    public List<User> getUserList() {
        return chatService.getUserList();
    }

    @PostMapping("/add/user/")
    public List<User> addUser(@RequestBody  User user) {
        return chatService.addUser(user);
    }

    @PostMapping ("/remove/user/")
    public List<User> removeUser(@RequestBody User user) {
        return chatService.removeUser(user);
    }

    @Autowired
    private ChatService chatService;
}
