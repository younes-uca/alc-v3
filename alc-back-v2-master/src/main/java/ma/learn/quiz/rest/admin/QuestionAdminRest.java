package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.Question;
import ma.learn.quiz.service.impl.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/question")
public class QuestionAdminRest {


    @GetMapping("/ref/{ref}")
    public Question findByRef(@PathVariable String ref) {
        return questionService.findByRef(ref);
    }

    @Transactional
    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return questionService.deleteByRef(ref);
    }

    @GetMapping("/")
    public List<Question> findAll() {
        return questionService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody Question question) {
        return questionService.save(question);
    }

    @GetMapping("/quiz/ref/{ref}")
    public List<Question> findByQuizRef(@PathVariable String ref) {
        return questionService.findByQuizRef(ref);
    }

    @DeleteMapping("/quiz/ref/{ref}")
    public int deleteByQuizRef(@PathVariable String ref) {
        return questionService.deleteByQuizRef(ref);
    }

    @PutMapping("/")
    public void update(@RequestBody Question question) {
        questionService.update(question);
    }

    @GetMapping("/numero/{numero}")
    public Question findByNumero(@PathVariable Long numero) {
        return questionService.findByNumero(numero);
    }


    @PostMapping("/deleteQst/")
    public void deleteById(@RequestBody Question qst) {
        questionService.deleteById(qst);
    }

    @GetMapping("/id/{id}")
    public Optional<Question> findById(@PathVariable Long id) {
        return questionService.findById(id);
    }

    @Autowired
    private QuestionService questionService;

    @GetMapping("/quiz/ref/{ref}/numero/{numero}")
    public Object findByQuizAndNumero(@PathVariable String ref, @PathVariable Long numero) {
        return questionService.findByQuizAndNumero(ref, numero);
    }

}
