package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.HomeWorkQuestion;
import ma.learn.quiz.bean.HoweWorkQSTReponse;
import ma.learn.quiz.service.impl.HomeWorkQSTReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("etudiant/homeWorkqstReponse/")
public class HomeWorkQSTReponsesEtudiantRest {

    @Autowired
    private HomeWorkQSTReponseService homeWorkQSTReponseService;


    @GetMapping("question/{id}")
    public List<HoweWorkQSTReponse> findByHomeWorkQuestionId(@PathVariable Long id) {
        return homeWorkQSTReponseService.findByHomeWorkQuestionId(id);
    }

    @GetMapping("etat/{etatreponse}")
    public List<HoweWorkQSTReponse> findByEtatReponse(@PathVariable String etatreponse) {
        return homeWorkQSTReponseService.findByEtatReponse(etatreponse);
    }

    @PostMapping("/")
    public int save(@RequestBody HomeWorkQuestion homeWorkQuestion, @RequestBody List<HoweWorkQSTReponse> reponses) {
        return homeWorkQSTReponseService.save(homeWorkQuestion, reponses);
    }
}
