package ma.learn.quiz.rest.etudiant;


import ma.learn.quiz.bean.PackStudent;
import ma.learn.quiz.service.impl.PackStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("packStudentEtudiant/")
public class PackStudentEtudiantrest {

    @Autowired
    private PackStudentService packStudentService;


    @GetMapping("code/{code}")
    public PackStudent findPackStudentByCode(@PathVariable String code) {
        return packStudentService.findPackStudentByCode(code);
    }

    @GetMapping("prix/{prox}")
    public PackStudent findPackStudentByPrix(@PathVariable Double prix) {
        return packStudentService.findPackStudentByPrix(prix);
    }

    @GetMapping("forGroupe/{forgroupe}")
    public List<PackStudent> findPackStudentByForGroupe(@PathVariable boolean forgroupe) {
        return packStudentService.findPackStudentByForGroupe(forgroupe);
    }
}
