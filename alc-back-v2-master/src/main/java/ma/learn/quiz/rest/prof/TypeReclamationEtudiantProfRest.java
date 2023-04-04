package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.TypeReclamationEtudiant;
import ma.learn.quiz.service.impl.TypeReclamationEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/prof/typeReclamationEtudiantAdmin")
public class TypeReclamationEtudiantProfRest {
    @Autowired
    private TypeReclamationEtudiantService typeReclamationEtudiantService;

    @GetMapping("/")

    public List<TypeReclamationEtudiant> findAll() {
        return typeReclamationEtudiantService.findAll();
    }
}
