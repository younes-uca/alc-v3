package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.InteretEtudiant;
import ma.learn.quiz.service.impl.InteretEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant/interetEtudiant")
public class InteretEtudiantRest {
    @Autowired
    private InteretEtudiantService interetEtudiantService;

    @GetMapping("/")
    public List<InteretEtudiant> findAll() {
        return interetEtudiantService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody InteretEtudiant interetEtudiant) {
        return interetEtudiantService.save(interetEtudiant);
    }

    @PutMapping("/")
    public int update(@RequestBody InteretEtudiant interetEtudiant) {
        return interetEtudiantService.update(interetEtudiant);
    }

    @DeleteMapping("/libelle/{libelle}")
    public int deleteByLibelle(@PathVariable String libelle) {
        return interetEtudiantService.deleteByLibelle(libelle);
    }
}
