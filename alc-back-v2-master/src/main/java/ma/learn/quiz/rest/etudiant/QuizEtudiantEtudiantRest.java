package ma.learn.quiz.rest.etudiant;


import ma.learn.quiz.bean.QuizEtudiant;
import ma.learn.quiz.exception.ObjectNotFoundException;
import ma.learn.quiz.service.impl.QuizEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant/quizEtudiant")
public class QuizEtudiantEtudiantRest {

    @Autowired
    private QuizEtudiantService quizEtudiantService;

    @GetMapping("/etudiant/{refEtudiant}/quiz/{refQuiz}")
    public Object findByCritere(@PathVariable String refEtudiant, @PathVariable String refQuiz) {
        return quizEtudiantService.findByCritere(refEtudiant, refQuiz);
    }


    @GetMapping("/etudiant/idEtudiant/{idEtudiant}/quiz/idQuiz/{idQuiz}")
    public QuizEtudiant findByEtudiantIdAndQuizId(@PathVariable Long idEtudiant, @PathVariable Long idQuiz) {
        return quizEtudiantService.findQuizEtudiantByQuizIdAndEtudiantId(idEtudiant, idQuiz);
    }

    @GetMapping("/etudiant/id/{id}")
    public List<QuizEtudiant> findByEtudiantId(@PathVariable Long id) {
        return quizEtudiantService.findByEtudiantId(id);
    }

    @GetMapping("/resultat/{resultat}")
    public List<QuizEtudiant> findByResultat(String resultat) {
        return quizEtudiantService.findByResultat(resultat);
    }

    @GetMapping("/ref/{ref}")
    public QuizEtudiant findByRef(@PathVariable String ref) {
        return quizEtudiantService.findByRef(ref);
    }

    @DeleteMapping("/id/{id}")
    public int deleteById(@PathVariable Long id) {
        return quizEtudiantService.deleteById(id);
    }

    @GetMapping("/quiz/ref/{ref}")
    public List<QuizEtudiant> findByQuizRef(@PathVariable String ref) {
        return quizEtudiantService.findByQuizRef(ref);
    }


    @DeleteMapping("/quiz/ref/{ref}")
    public int deleteByQuizRef(@PathVariable String ref) {
        return quizEtudiantService.deleteByQuizRef(ref);
    }


    @PostMapping("/")
    public QuizEtudiant save(@RequestBody QuizEtudiant quizEtudiant) throws ObjectNotFoundException {
        return quizEtudiantService.save(quizEtudiant);
    }

    @GetMapping("/")
    public List<QuizEtudiant> findAll() {
        return quizEtudiantService.findAll();
    }

    @PutMapping("/")
    public void update(@RequestBody QuizEtudiant quizEtudiant) {
        quizEtudiantService.update(quizEtudiant);
    }


}
