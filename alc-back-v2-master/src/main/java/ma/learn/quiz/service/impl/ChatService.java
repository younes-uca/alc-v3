package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private final List<User> userList = new ArrayList<User>();

    public List<User> getUserList() {
        return userList;
    }

    public List<User> addUser(User user) {
        this.userList.add(user);
        return this.userList;
    }

    public List<User> removeUser(User user) {
        this.userList.remove(user);
        return this.userList;
    }


}
