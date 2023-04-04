package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.Cours;
import ma.learn.quiz.service.impl.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/cours")

public class CoursPublicPublicRest {

    @Autowired
    private CoursService coursService;

    @GetMapping("/order/id/{id}")
    public List<Cours> findByCriteria(@PathVariable Long id) {
        return coursService.findByCriteria(id);
    }

    @GetMapping("/id/{id}")
    public int init(@PathVariable Long id) {
        return coursService.init(id);
    }

    @GetMapping("/libelle/{libelle}")
    public List<Cours> findByLibelle(@PathVariable String libelle) {
        return coursService.findByLibelle(libelle);
    }


    @DeleteMapping("/entity/{entity}")
    public void delete(Cours entity) {
        coursService.delete(entity);
    }

    @DeleteMapping("/id/{id}")
    public int deleteCoursById(@PathVariable Long id) {
        return coursService.deleteCoursById(id);
    }

    @GetMapping("/")
    public List<Cours> findAll() {
        return coursService.findAll();
    }


    @PutMapping("/")
    public Cours update(@RequestBody Cours cours) {
        return coursService.update(cours);
    }


    @DeleteMapping("/parcours/code/{code}")
    public int deleteByParcoursCode(@PathVariable String code) {
        return coursService.deleteByParcoursCode(code);
    }

    @GetMapping("/parcours/id/{id}")
    public List<Cours> findByParcoursId(@PathVariable Long id) {
        return coursService.findByParcoursId(id);
    }

    @PostMapping("/")
    public Cours save(@RequestBody Cours cours) throws Exception {
        return coursService.save(cours);
    }

    @PostMapping("/delete-multiple-by-id")
    public int deleteCoursById(@RequestBody List<Cours> courss) {
        return coursService.deleteCoursById(courss);
    }


}
