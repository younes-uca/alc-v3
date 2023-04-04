package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.SuperCategorieSection;
import ma.learn.quiz.service.impl.SuperCategorieSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant/supercategoriesection")
public class SuperCategorieSectionEtudiantRest {
    @Autowired
    private SuperCategorieSectionService superCategorieSectionService;

    @GetMapping("/code/{code}")
    public SuperCategorieSection findByCode(@PathVariable String code) {
        return superCategorieSectionService.findByCode(code);
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return superCategorieSectionService.deleteByCode(code);
    }

    @PostMapping("/")
    public int save(@RequestBody SuperCategorieSection superCategorieSection) {
        return superCategorieSectionService.save(superCategorieSection);
    }

    @GetMapping("/")
    public List<SuperCategorieSection> findAll() {
        return superCategorieSectionService.findAll();
    }

    @PutMapping("/")
    public void update(@RequestBody SuperCategorieSection superCategorieSection) {
        superCategorieSectionService.update(superCategorieSection);
    }

    @DeleteMapping("/entity/{entity}")
    public void deleteAll() {
        superCategorieSectionService.deleteAll();
    }

    @GetMapping("/libelle/{libelle}")
    public List<SuperCategorieSection> findByLibelle(@PathVariable String libelle) {
        return superCategorieSectionService.findByLibelle(libelle);
    }


}
