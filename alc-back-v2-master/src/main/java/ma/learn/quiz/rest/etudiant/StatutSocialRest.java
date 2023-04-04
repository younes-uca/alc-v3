package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.StatutSocial;
import ma.learn.quiz.service.impl.StatutSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant/statutSocial")
public class StatutSocialRest {
    @Autowired
    private StatutSocialService statutSocialService;

    @GetMapping("/")
    public List<StatutSocial> findAll() {
        return statutSocialService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody StatutSocial statutSocial) {
        return statutSocialService.save(statutSocial);
    }

    @PutMapping("/")
    public int update(@RequestBody StatutSocial statutSocial) {
        return statutSocialService.update(statutSocial);
    }

    @DeleteMapping("/libelle/{libelle}")
    public int deleteByLibelle(@PathVariable String libelle) {
        return statutSocialService.deleteByLibelle(libelle);
    }
}
