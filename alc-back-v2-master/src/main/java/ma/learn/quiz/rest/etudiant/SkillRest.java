package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.Skill;
import ma.learn.quiz.service.impl.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant/skill")
public class SkillRest {
    @Autowired
    private SkillService skillService;

    @GetMapping("/")
    public List<Skill> findAll() {
        return skillService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody Skill skill) {
        return skillService.save(skill);
    }

    @PutMapping("/")
    public int update(@RequestBody Skill skill) {
        return skillService.update(skill);
    }

    @DeleteMapping("/libelle/{libelle}")
    public int deleteByLibelle(@PathVariable String libelle) {
        return skillService.deleteByLibelle(libelle);
    }

}

