package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.Quiz;
import ma.learn.quiz.service.impl.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/quiz")
public class QuizAdminRest {
    @PutMapping("/")
    public void update(@RequestBody Quiz quiz) {
        quizService.update(quiz);
    }


    @PostMapping("/update/")
    public Quiz updateAll(@RequestBody Quiz quiz) {
        return quizService.updateAll(quiz);
    }

    @Autowired
    private QuizService quizService;

    @GetMapping("/section/id/{id}")
    public Quiz findBySectionId(@PathVariable Long id) {
        return quizService.findBySectionId(id);
    }

    @GetMapping("/ref/{ref}")
    public Quiz findByRef(@PathVariable String ref) {
        return quizService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return quizService.deleteByRef(ref);
    }

    @PostMapping("/deleteAll/")
    public void deleteById(@RequestBody Quiz quiz) {
        quizService.deleteById(quiz);
    }

    @GetMapping("/")
    public List<Quiz> findAll() {
        return quizService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody Quiz quiz) {
        return quizService.save(quiz);
    }

    @PostMapping("/save/")
    public int saveAll(@RequestBody Quiz quiz) {
        return quizService.saveAll(quiz);
    }

}
