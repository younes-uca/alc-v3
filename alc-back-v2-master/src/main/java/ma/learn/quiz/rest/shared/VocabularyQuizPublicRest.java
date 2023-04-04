package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.VocabularyQuiz;
import ma.learn.quiz.service.impl.VocabularyQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/vocabularyQuiz")
public class VocabularyQuizPublicRest {

    @PutMapping("/")
    public void update(@RequestBody VocabularyQuiz vocabularyQuiz) {
        vocabularyQuizService.update(vocabularyQuiz);
    }

    @GetMapping("/ref/{ref}")
    public VocabularyQuiz findByRef(@PathVariable String ref) {
        return vocabularyQuizService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return vocabularyQuizService.deleteByRef(ref);
    }

    @GetMapping("/section/id/{id}")
    public VocabularyQuiz findBySectionId(@PathVariable Long id) {
        return vocabularyQuizService.findBySectionId(id);
    }

    @PostMapping("/")
    public int save(@RequestBody VocabularyQuiz vocabularyQuiz) {
        return vocabularyQuizService.save(vocabularyQuiz);
    }

    @GetMapping("/")
    public List<VocabularyQuiz> findAll() {
        return vocabularyQuizService.findAll();
    }

    @Autowired
    private VocabularyQuizService vocabularyQuizService;
}
