package ma.learn.quiz.rest.admin;


import ma.learn.quiz.bean.PackStudent;
import ma.learn.quiz.service.impl.PackStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/packStudent")
public class PackStudentAdminRest {

    @Autowired
    private PackStudentService packStudentService;


    @GetMapping("/level/id/{id}")
    public List<PackStudent> findByLevelId(@PathVariable Long id) {
        return packStudentService.findByLevelId(id);
    }


    @GetMapping("/id/{id}")
    public PackStudent findById(@PathVariable Long id) {
        return packStudentService.findById(id);
    }

    @GetMapping("/")
    public List<PackStudent> findAll() {
        return packStudentService.findAll();
    }

    @GetMapping("/code/{code}")
    public PackStudent findPackStudentByCode(@PathVariable String code) {
        return packStudentService.findPackStudentByCode(code);
    }

    @GetMapping("/prix/{prix}")
    public PackStudent findPackStudentByPrix(@PathVariable Double prix) {
        return packStudentService.findPackStudentByPrix(prix);
    }

    @GetMapping("/packForgroupe/{forgroupe}")
    public List<PackStudent> findPackStudentByForGroupe(@PathVariable boolean forgroupe) {
        return packStudentService.findPackStudentByForGroupe(forgroupe);
    }

    @PostMapping("/criteria")
    public List<PackStudent> findbyCriteria(@RequestBody PackStudent packStudent) {
        return packStudentService.findbyCriteria(packStudent);
    }

    @GetMapping("/libelle/{libelle}")
    public PackStudent findPackStudentByLibelle(@PathVariable String libelle) {
        return packStudentService.findPackStudentByLibelle(libelle);
    }

    @GetMapping("/totalStudents/{totalStudents}")
    public List<PackStudent> findByTotalStudents(@PathVariable int totalStudents) {
        return packStudentService.findByTotalStudents(totalStudents);
    }

    @PostMapping("/")
    public PackStudent save(@RequestBody PackStudent packStudent) throws Exception {
        return packStudentService.save(packStudent);
    }

    @PutMapping("/")
    public PackStudent update(@RequestBody PackStudent packStudent) {
        return packStudentService.update(packStudent);
    }

    @DeleteMapping("/code/{code}")
    public void deleteByCode(@PathVariable Long code) {
        packStudentService.deleteById(code);
    }


    @DeleteMapping("/forgroupe/{forGroupe}")
    public int deleteByForGroupe(@PathVariable boolean forGroupe) {
        return packStudentService.deleteByForGroupe(forGroupe);
    }
}
