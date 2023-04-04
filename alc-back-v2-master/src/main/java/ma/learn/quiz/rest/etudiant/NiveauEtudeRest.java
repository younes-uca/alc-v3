package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.NiveauEtude;
import ma.learn.quiz.service.impl.NiveauEtudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant/niveauEtude")
public class NiveauEtudeRest {
    @Autowired
    private NiveauEtudeService niveauEtudeService;

    @GetMapping("/")
    public List<NiveauEtude> findAll() {
        return niveauEtudeService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody NiveauEtude niveauEtude) {
        return niveauEtudeService.save(niveauEtude);
    }

    @PutMapping("/")
    public int update(@RequestBody NiveauEtude niveauEtude) {
        return niveauEtudeService.update(niveauEtude);
    }

    @DeleteMapping("/libelle/{libelle}")
    public int deleteByLibelle(@PathVariable String libelle) {
        return niveauEtudeService.deleteByLibelle(libelle);
    }
}
