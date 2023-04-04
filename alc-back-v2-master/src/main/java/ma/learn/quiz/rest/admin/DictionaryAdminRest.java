package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.Dictionary;
import ma.learn.quiz.service.impl.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dictionary")
public class DictionaryAdminRest {
    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/word/{word}")
    public Dictionary findByWord(@PathVariable String word) {
        return dictionaryService.findByWord(word);
    }

    @GetMapping("/id/{id}")
    public Dictionary findDictionaryById(@PathVariable Long id) {
        return dictionaryService.findDictionaryById(id);
    }

    @GetMapping("/word/{word}/Etudiant/id/{id}")
    public Dictionary findByWordAndEtudiantId(@PathVariable String word, @PathVariable Long id) {
        return dictionaryService.findByWordAndEtudiantId(word, id);
    }

    @GetMapping("/etudiant/id/{id}")
    public List<Dictionary> findByEtudiantId(@PathVariable Long id) {
        return dictionaryService.findByEtudiantId(id);
    }

    @DeleteMapping("/words/{word}/Etudiant/id/{id}")
    public int deleteByWordAndEtudiantId(@PathVariable String word, @PathVariable Long id) {
        return dictionaryService.deleteByWordAndEtudiantId(word, id);
    }

    @PutMapping("/")
    public int update(@RequestBody Dictionary dictionary) {
        return dictionaryService.update(dictionary);
    }

    @GetMapping("/")
    public List<Dictionary> findAll() {
        return dictionaryService.findAll();
    }

    @PostMapping("/")
    public Dictionary Dictionary(@RequestBody Dictionary dictionary) {
        System.out.println("haddii hya" + dictionary);
        return dictionaryService.save(dictionary);
    }


}