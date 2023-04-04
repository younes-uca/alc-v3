package ma.learn.quiz.rest.prof;


import ma.learn.quiz.bean.Parcours;
import ma.learn.quiz.service.impl.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prof/parcours")

public class ParcoursProfRest {

    @Autowired
    public ParcoursService parcoursService;

    @GetMapping("/")
    public List<Parcours> findAll() {
        return parcoursService.findAll();
    }


    @GetMapping("/code/{code}")
    public List<Parcours> findByCode(@PathVariable String code) {
        return parcoursService.findByCode(code);
    }


    @DeleteMapping("/entity/{entity}")
    public void delete(@PathVariable Parcours entity) {
        parcoursService.delete(entity);
    }

    @PostMapping("/")
    public int save(@RequestBody Parcours parcours) throws Exception {
        return parcoursService.save(parcours);
    }

    @PutMapping("/")
    public Parcours update(@RequestBody Parcours parcours) {
        return parcoursService.update(parcours);
    }


    @DeleteMapping("/id/{id}")
    public int deleteParcoursById(@PathVariable Long id) {
        return parcoursService.deleteParcoursById(id);
    }

    @PostMapping("/delete-multiple-by-id")
    public int deleteParcoursById(@RequestBody List<Parcours> parcourss) {
        return parcoursService.deleteParcoursById(parcourss);
    }

}
