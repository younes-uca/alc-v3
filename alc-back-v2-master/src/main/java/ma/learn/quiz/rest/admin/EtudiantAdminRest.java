package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.service.impl.EtudiantService;
import ma.learn.quiz.service.vo.EtudiantVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/etudiant")
public class EtudiantAdminRest {
    @Autowired
    public EtudiantService etudiantService;


    @PostMapping("/search")
    public List<Etudiant> findByCriteria(@RequestBody EtudiantVo etudiantVo) {
        return etudiantService.findByCriteria(etudiantVo);
    }

    @GetMapping("/parcours/code/{code}")
    public List<Etudiant> findByParcoursCode(@PathVariable String code) {
        return etudiantService.findByParcoursCode(code);
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

    @PostMapping("/search-all/")
    public List<Etudiant> findByCriteria(@RequestBody Etudiant etudiant) {
        return etudiantService.findByCriteria(etudiant);
    }

    @GetMapping("/login/{login}/password/{password}")
    public Object findByCritere(@PathVariable String login, @PathVariable String password) {
        return etudiantService.findByCritere(login, password);
    }

    @PutMapping("/")
    public Etudiant update(@RequestBody Etudiant etudiant) {
        return etudiantService.update(etudiant);
    }

    @GetMapping("/libelle/{libelle}")
    public List<Etudiant> findByParcoursLibelle(@PathVariable String libelle) {
        return etudiantService.findByParcoursLibelle(libelle);
    }

}
