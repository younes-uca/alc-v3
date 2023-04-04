package ma.learn.quiz.rest.shared;


import ma.learn.quiz.bean.ReponseEtudiant;
import ma.learn.quiz.service.impl.ReponseEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/reponseEtudiant")
public class ReponseEtudiantPublicRest {

    @Transactional
    @DeleteMapping("/quizEtudiant/id/{id}")
    public int deleteByQuizEtudiantId(@PathVariable Long id) {
        return reponseEtudiantService.deleteByQuizEtudiantId(id);
    }

    @Autowired
    private ReponseEtudiantService reponseEtudiantService;


    @GetMapping("/creteria/quizEtudiant/id/{idQuizEtudiant}/question/id/{idQuestion}")
    public List<ReponseEtudiant> findByCriteria(@PathVariable Long idQuizEtudiant, @PathVariable Long idQuestion) {
        return reponseEtudiantService.findByCriteria(idQuizEtudiant, idQuestion);
    }

    @GetMapping("/quizEtudiant/ref/{ref}")
    public List<ReponseEtudiant> findByQuizEtudiantRef(@PathVariable String ref) {
        return reponseEtudiantService.findByQuizEtudiantRef(ref);
    }

    @GetMapping("/ref/{ref}")
    public ReponseEtudiant findByRef(@PathVariable String ref) {
        return reponseEtudiantService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String Ref) {
        return reponseEtudiantService.deleteByRef(Ref);
    }

    @GetMapping("/reponse/question/ref/{ref}")
    public List<ReponseEtudiant> findByReponseQuestionRef(@PathVariable String ref) {
        return reponseEtudiantService.findByReponseQuestionRef(ref);
    }

    @GetMapping("/quizEtudiant/etudiant/ref/{ref}")
    public List<ReponseEtudiant> findByQuizEtudiantEtudiantRef(@PathVariable String ref) {
        return reponseEtudiantService.findByQuizEtudiantEtudiantRef(ref);
    }

    @DeleteMapping("/reponse/question/ref/{ref}")
    public int deleteByReponseQuestionRef(@PathVariable String ref) {
        return reponseEtudiantService.deleteByReponseQuestionRef(ref);
    }

    @DeleteMapping("/quizEtudiant/etudiant/ref/{ref}")
    public int deleteByQuizEtudiantEtudiantRef(@PathVariable String ref) {
        return reponseEtudiantService.deleteByQuizEtudiantEtudiantRef(ref);
    }

    @PostMapping("/")
    public ReponseEtudiant save(@RequestBody ReponseEtudiant reponseEtudiant) {
        return reponseEtudiantService.save(reponseEtudiant);
    }

    @GetMapping("/")
    public List<ReponseEtudiant> findAll() {
        return reponseEtudiantService.findAll();
    }


}
