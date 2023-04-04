package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.InviteStudent;
import ma.learn.quiz.service.impl.InviteStudentService;
import ma.learn.quiz.service.vo.InviteStudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/inviteStudentAdmin")
public class InviteStudentAdminRest {
    @Autowired
    private InviteStudentService inviteStudentService;

    @GetMapping("/")
    public List<InviteStudent> findAll() {
        return inviteStudentService.findAll();
    }

    @PostMapping("/")
    public List<InviteStudent> findAllByCriteria(@RequestBody InviteStudentVo inviteStudentVo) {
        return inviteStudentService.findAllByCriteria(inviteStudentVo);
    }
}
