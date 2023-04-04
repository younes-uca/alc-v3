package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.Vocabulary;
import ma.learn.quiz.service.impl.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/prof/vocabulary")
public class VocabularyProfRest {

    @GetMapping("/numero/{numero}/section/id/{id}")
    public List<Vocabulary> findByNumeroAndSectionId(@PathVariable Long numero, @PathVariable Long id) {
        return vocabularyService.findByNumeroAndSectionId(numero, id);
    }

    @GetMapping("/numero/{numero}")
    public Vocabulary findByNumero(Long numero) {
        return vocabularyService.findByNumero(numero);
    }

    @GetMapping("/section/id/{id}")
    public List<Vocabulary> findBySectionId(@PathVariable Long id) {
        return vocabularyService.findBySectionId(id);
    }

    @PostMapping("/save/")
    public int saveAll(@RequestBody Vocabulary vocabulary) {
        return vocabularyService.saveAll(vocabulary);
    }

    @GetMapping("/quiz/ref/{ref}")
    public List<Vocabulary> findByVocabularyQuizRef(String ref) {
        return vocabularyService.findByVocabularyQuizRef(ref);
    }

    @PutMapping("/")
    public void update(@RequestBody Vocabulary vocabulary) {
        vocabularyService.update(vocabulary);
    }

    @GetMapping("/ref/{ref}")
    public Vocabulary findByRef(@PathVariable String ref) {
        return vocabularyService.findByRef(ref);
    }

    @Transactional
    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return vocabularyService.deleteByRef(ref);
    }


    @GetMapping("/")
    public List<Vocabulary> findAll() {
        return vocabularyService.findAll();
    }

    @Autowired
    private VocabularyService vocabularyService;
}
