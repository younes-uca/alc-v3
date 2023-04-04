package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.ReclamationProf;
import ma.learn.quiz.service.impl.ReclamationProfService;
import ma.learn.quiz.service.vo.ReclamationProfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController

@RequestMapping("/admin/reclamationProfAdmin")
public class ReclamationProfAdminRest {

    @Autowired
    private ReclamationProfService reclamationProfService;

    @GetMapping("/")

    public List<ReclamationProf> findAll() {
        return reclamationProfService.findAll();
    }


    @PutMapping("/reponseProf/{dateTraitement}")
    public int reponseReclamationProf(@RequestBody ReclamationProf reclamationProf, @PathVariable Date dateTraitement) {
        return reclamationProfService.reponseReclamationProf(reclamationProf, dateTraitement);
    }

    @GetMapping("/{id}")

    public ReclamationProf findReclamationProfById(@PathVariable Long id) {
        return reclamationProfService.findReclamationProfById(id);
    }

    @GetMapping("/prof/{id}")

    public List<ReclamationProf> findReclamationProfByProfId(@PathVariable Long id) {
        return reclamationProfService.findReclamationProfByProfId(id);
    }

    @PostMapping("/byCriteria")
    public List<ReclamationProf> findAllByCriteria(@RequestBody ReclamationProfVo reclamationProfVo) {
        return reclamationProfService.findAllByCriteria(reclamationProfVo);
    }

    @GetMapping("/idReclamationProf1/{idReclamationProf1}")

    public int viewReclamationProf(@PathVariable Long idReclamationProf1) {
        return reclamationProfService.viewReclamationProf(idReclamationProf1);
    }
}
