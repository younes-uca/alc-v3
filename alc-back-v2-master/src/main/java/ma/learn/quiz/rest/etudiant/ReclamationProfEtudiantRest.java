package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.ReclamationProf;
import ma.learn.quiz.service.impl.ReclamationProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/etudiant/reclamationProfAdmin")
public class ReclamationProfEtudiantRest {
    @Autowired
    private ReclamationProfService reclamationProfService;

    @GetMapping("/")

    public List<ReclamationProf> findAll() {
        return reclamationProfService.findAll();
    }

    @PostMapping("/")
    public int saveReclamationProf(@RequestBody ReclamationProf reclamationProf) {
        return reclamationProfService.saveReclamationProf(reclamationProf);
    }
}
