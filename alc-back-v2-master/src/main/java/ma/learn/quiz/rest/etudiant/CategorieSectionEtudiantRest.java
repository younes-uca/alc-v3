package ma.learn.quiz.rest.etudiant;


import ma.learn.quiz.bean.CategorieSection;
import ma.learn.quiz.service.impl.CategorieSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant/categoriesection")
public class CategorieSectionEtudiantRest {

    @Autowired
    public CategorieSectionService categorieSectionService;

    @PostMapping("/")
    public int save(@RequestBody CategorieSection categorieSection) {
        return categorieSectionService.save(categorieSection);
    }

    @GetMapping("/code/{code}")
    public CategorieSection findByCode(@PathVariable String code) {
        return categorieSectionService.findByCode(code);
    }


    @GetMapping("/")
    public List<CategorieSection> findAll() {
        return categorieSectionService.findAll();
    }

    @PutMapping("/")
    public void update(@RequestBody CategorieSection categorieSection) {
        categorieSectionService.update(categorieSection);
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return categorieSectionService.deleteByCode(code);
    }

    @GetMapping("/libelle/{libelle}")
    public List<CategorieSection> findByLibelle(@PathVariable String libelle) {
        return categorieSectionService.findByLibelle(libelle);
    }


}

