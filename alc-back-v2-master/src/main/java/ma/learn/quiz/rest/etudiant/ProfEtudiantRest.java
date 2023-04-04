package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.Prof;
import ma.learn.quiz.bean.Salary;
import ma.learn.quiz.bean.SessionCours;
import ma.learn.quiz.service.impl.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant/prof")
public class ProfEtudiantRest {

    @GetMapping("/numero/{numro}")
    public Prof findByNumero(@PathVariable String numro) {
        return profService.findByNumero(numro);
    }

    @DeleteMapping("/numero/{numero}")
    public int deleteByNumero(@PathVariable String numero) {
        return profService.deleteByNumero(numero);
    }

    @GetMapping("/id/{id}")
    public Prof findProfById(@PathVariable Long id) {
        return profService.findProfById(id);
    }

    @GetMapping("/")
    public List<Prof> findAll() {
        return profService.findAll();
    }

    @PostMapping("/")
    public Prof save(@RequestBody Prof prof) throws Exception {
        return profService.save(prof);
    }

    @PostMapping("/salary/{salaryVo}")
    public List<SessionCours> calcStatistique(@RequestBody Salary salaryVo) {
        return profService.calcStatistique(salaryVo);
    }

    @GetMapping("/nom/{nom}")
    public Prof findByNom(@PathVariable String nom) {
        return profService.findByNom(nom);
    }

    @GetMapping("/ref/{ref}")
    public Prof findByRef(@PathVariable String ref) {
        return profService.findByRef(ref);
    }

    @PostMapping("/delete-multiple-by-id")
    public int deleteProfById(@RequestBody List<Prof> prof) {
        return profService.deleteProfById(prof);
    }

    @DeleteMapping("/id/{id}")
    public int deleteProfById(@PathVariable Long id) {
        return profService.deleteProfById(id);
    }

    @GetMapping("/login/{login}/password/{password}")
    public Object findByCritere(@PathVariable String login, @PathVariable String password) {
        return profService.findByCritere(login, password);
    }

    @PutMapping("/")
    public Prof update(@RequestBody Prof prof) {
        return profService.update(prof);
    }

    @PostMapping("/search")
    public List<Prof> findByCriteria(@RequestBody Prof prof) {
        return profService.findByCriteria(prof);
    }

    @GetMapping("/sessionNonPayer/prof/id/{idProf}")
    public List<SessionCours> findSessionsNonPayer(@PathVariable Long idProf) {
        return profService.findSessionsNonPayer(idProf);
    }

    @Autowired
    private ProfService profService;

}