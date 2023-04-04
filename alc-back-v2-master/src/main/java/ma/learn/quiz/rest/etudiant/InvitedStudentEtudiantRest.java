package ma.learn.quiz.rest.etudiant;

import freemarker.template.TemplateException;
import ma.learn.quiz.bean.InviteStudent;
import ma.learn.quiz.service.impl.InviteStudentService;
import ma.learn.quiz.service.vo.InviteStudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/etudiant/invitedStudentEtudiant")
public class InvitedStudentEtudiantRest {
    @Autowired
    private InviteStudentService inviteStudentService;

    @GetMapping("/{id}")
    public List<InviteStudent> findInviteStudentByEtudiantId(@PathVariable Long id) {
        return inviteStudentService.findInviteStudentByEtudiantId(id);
    }

    @GetMapping("/emailInvited/{emailInvited}")

    public InviteStudent findInviteStudentByEmailInvited(@PathVariable String emailInvited) {
        return inviteStudentService.findInviteStudentByEmailInvited(emailInvited);
    }

    @GetMapping("/email/{emailInvited}/code/{code}")
    public InviteStudent findInviteStudentByEmailInvitedAndCode(@PathVariable String emailInvited, @PathVariable String code) {
        return inviteStudentService.findInviteStudentByEmailInvitedAndCode(emailInvited, code);
    }

    @PostMapping("/")
    public List<InviteStudent> findAllByCriteria(@RequestBody InviteStudentVo inviteStudentVo) {
        return inviteStudentService.findAllByCriteria(inviteStudentVo);
    }

    @GetMapping("/{idStudent}/{emailInvited}")
    public int saveInvitation(@PathVariable Long idStudent, @PathVariable String emailInvited) throws MessagingException, TemplateException, IOException {
        return inviteStudentService.saveInvitation(idStudent, emailInvited);
    }
}
