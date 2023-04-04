package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.TypeReclamationProf;
import ma.learn.quiz.service.impl.TypeReclamationProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prof/typeReclamationProfAdmin")
public class TypeReclamationProfProfRest {
    @Autowired
    private TypeReclamationProfService typeReclamationProfService;

    @GetMapping("/")
    public List<TypeReclamationProf> findAll() {
        return typeReclamationProfService.findAll();
    }
}
