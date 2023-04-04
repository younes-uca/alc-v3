package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.bean.User;
import ma.learn.quiz.service.impl.EtudiantService;
import ma.learn.quiz.service.vo.EtudiantVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant/etudiant")
public class EtudiantEtudiantRest {

    @GetMapping("/id/{id}")
    public Etudiant findEtudiantById(@PathVariable Long id) {
        return etudiantService.findEtudiantById(id);
    }

    @Autowired
    public EtudiantService etudiantService;


    @PostMapping("/save/")
    public User create(@RequestBody Etudiant etudiant) throws Exception {
        return etudiantService.create(etudiant);
    }


    @PostMapping("/save/pack/{pack}")
    public User create(@PathVariable String pack, @RequestBody Etudiant etudiant) throws Exception {
        if (pack == null) {
            return etudiantService.create(etudiant);
        }
        Long packId = Long.valueOf(pack);
        return etudiantService.create(packId, etudiant);
    }


    @PostMapping("/search")
    public List<Etudiant> findByCriteria(@RequestBody EtudiantVo etudiantVo) {
        return etudiantService.findByCriteria(etudiantVo);
    }

    @GetMapping("/parcours/code/{code}")
    public List<Etudiant> findByParcoursCode(@PathVariable String code) {
        return etudiantService.findByParcoursCode(code);
    }

    @GetMapping("/username/{username}/newpass/{newPassword}")
    public int updatePassword(@PathVariable String username, @PathVariable String newPassword) {
        return etudiantService.updatePassword(username, newPassword);
    }

    @PostMapping("/")
    public int save(@RequestBody Etudiant etudiant) {
        return etudiantService.save(etudiant);
    }

    @GetMapping("/ref/{ref}")
    public Etudiant findByRef(@PathVariable String ref) {
        return etudiantService.findByRef(ref);
    }


    @GetMapping("/nom/{nom}")
    public Etudiant findByNom(@PathVariable String nom) {
        return etudiantService.findByNom(nom);
    }


    @GetMapping("/")
    public List<Etudiant> findAll() {
        return etudiantService.findAll();
    }

    @PostMapping("/delete-multiple-by-id")
    public int deleteEtudiantById(@RequestBody List<Etudiant> etudiant) {
        return etudiantService.deleteEtudiantById(etudiant);
    }

    @DeleteMapping("/id/{id}")
    public int deleteByEtudiantId(@PathVariable Long id) {
        return etudiantService.deleteEtudiantById(id);
    }

    @GetMapping("/login/{login}/password/{password}")
    public Object findByCritere(@PathVariable String login, @PathVariable String password) {
        return etudiantService.findByCritere(login, password);
    }

    @GetMapping("/validate/{token}")
    public Etudiant validate(@PathVariable String token) throws Exception {
        return etudiantService.validateToken(token);
    }

    @PutMapping("/")
    public Etudiant update(@RequestBody Etudiant etudiant) {
        return etudiantService.update(etudiant);
    }


}
