package ma.learn.quiz.rest.etudiant;


import ma.learn.quiz.bean.Reponse;
import ma.learn.quiz.service.impl.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etudiant/reponse")
public class ReponseEtudiantRest {
    @GetMapping("/question/id/{id}")
    public List<Reponse> findByQuestionId(@PathVariable Long id) {
        return reponseService.findByQuestionId(id);
    }

    @GetMapping("/ref/{ref}")
    public Reponse findByRef(@PathVariable String ref) {
        return reponseService.findByRef(ref);
    }

    @Transactional
    @DeleteMapping("/ref/{ref}")

    public int deleteByRef(@PathVariable String ref) {
        return reponseService.deleteByRef(ref);
    }

    @GetMapping("/")
    public List<Reponse> findAll() {
        return reponseService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody Reponse reponse) {
        return reponseService.save(reponse);
    }

    @Transactional
    @DeleteMapping("/question/ref/{ref}")
    public int deleteByQuestionRef(@PathVariable String ref) {
        return reponseService.deleteByQuestionRef(ref);
    }

    @Transactional
    @DeleteMapping("/question/quiz/ref/{ref}")
    public int deleteByQuestionQuizRef(@PathVariable String ref) {
        return reponseService.deleteByQuestionQuizRef(ref);
    }

    @DeleteMapping("/question/id/{id}")
    public int deleteByQuestionId(@PathVariable Long id) {
        return reponseService.deleteByQuestionId(id);
    }

    @Transactional
    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id) {
        reponseService.deleteById(id);
    }

    @PostMapping("/save/")
    public void saveAnswer(@RequestBody Reponse reponse) {
        reponseService.saveAnswer(reponse);
    }

    @Autowired
    private ReponseService reponseService;

    @GetMapping("/id/{id}")
    public Optional<Reponse> findById(@PathVariable Long id) {
        return reponseService.findById(id);
    }

    @GetMapping("/etatReponse/{etatReponse}")
    public List<Reponse> findByEtatReponse(@PathVariable String etatReponse) {
        return reponseService.findByEtatReponse(etatReponse);
    }

    @GetMapping("/question/numero/{numero}")
    public List<Reponse> findByQuestionNumero(@PathVariable Long numero) {
        return reponseService.findByQuestionNumero(numero);
    }

    @GetMapping("/criteria/id/{id}")
    public List<Reponse> findByCriterial(@PathVariable Long id) {
        return reponseService.findByCriterial(id);
    }

}
