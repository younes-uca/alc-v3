package ma.learn.quiz.socketConfiguration;

import ma.learn.quiz.bean.User;
import ma.learn.quiz.dao.UserDao;
import ma.learn.quiz.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/socket/")
public class SocketController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SocketIOConfig socketIOConfig;

    @GetMapping("connected-user/{key}")
    public List<UserVo> getConnectedUsers(@PathVariable String key) {
        List<UserVo> userVoList = new ArrayList<>();
        List<String> ids = socketIOConfig.getConnectedUsers(key);
        if (ids != null) {
            for (String id : ids
            ) {
                User user = userDao.findById(Long.valueOf(id)).get();
                UserVo u = new UserVo(user.getId(), user.getNom(), user.getUsername());
                userVoList.add(u);
            }
            return userVoList;
        }
        return null;
    }
}
