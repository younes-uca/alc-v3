package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.Fonction;
import ma.learn.quiz.service.impl.FonctionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/etudiant/fonction")
public class FonctionRest {
    @Autowired
    private FonctionsService fonctionsService;

    @GetMapping("/")
    public List<Fonction> findAll() {
        return fonctionsService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody Fonction fonction) {
        return fonctionsService.save(fonction);
    }

    @PutMapping("/")
    public int update(@RequestBody Fonction fonction) {
        return fonctionsService.update(fonction);
    }

    @DeleteMapping("/libelle/{libelle}")
    public int deleteByLibelle(@PathVariable String libelle) {
        return fonctionsService.deleteByLibelle(libelle);
    }
}
