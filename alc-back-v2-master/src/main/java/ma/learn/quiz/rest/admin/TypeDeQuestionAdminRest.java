package ma.learn.quiz.rest.admin;


import ma.learn.quiz.bean.TypeDeQuestion;
import ma.learn.quiz.service.impl.TypeDeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/admin/TypeDeQuestion")
public class TypeDeQuestionAdminRest {

    @GetMapping("/ref/{ref}")
    public TypeDeQuestion findByRef(@PathVariable String ref) {
        return typeDeQuestionService.findByRef(ref);
    }

    @Transactional
    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return typeDeQuestionService.deleteByRef(ref);
    }

    @GetMapping("/")
    public List<TypeDeQuestion> findAll() {
        return typeDeQuestionService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody TypeDeQuestion typeDeQuestion) {
        return typeDeQuestionService.save(typeDeQuestion);
    }

    @PutMapping("/")
    public void update(@RequestBody TypeDeQuestion typeDeQuestion) {
        typeDeQuestionService.update(typeDeQuestion);
    }

    @Autowired
    private TypeDeQuestionService typeDeQuestionService;
}
