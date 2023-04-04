package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.bean.User;
import ma.learn.quiz.service.impl.EtudiantService;
import ma.learn.quiz.service.vo.EtudiantVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/etudiant")
public class EtudiantPublicRest {
    @Autowired
    public EtudiantService etudiantService;


    @PostMapping("/start/test")
    public ResponseEntity<Etudiant> startLevelTest(@RequestBody Etudiant etudiant) {
        return etudiantService.startLevelTest(etudiant);
    }

    @PostMapping("/verify/email/{code}")
    public int verifyEmail(@RequestBody Etudiant etudiant, @PathVariable String code) throws Exception {
        return etudiantService.verifyEmail(etudiant, Integer.parseInt(code));
    }

    @PostMapping("/search")
    public List<Etudiant> findByCriteria(@RequestBody EtudiantVo etudiantVo) {
        return etudiantService.findByCriteria(etudiantVo);
    }

    @GetMapping("/parcours/code/{code}")
    public List<Etudiant> findByParcoursCode(@PathVariable String code) {
        return etudiantService.findByParcoursCode(code);
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


    @GetMapping("/number-of-student")
    public long getNumberOfStudent() {
        return etudiantService.getNumberOfStudents();
    }

    @DeleteMapping("/id/{id}")
    public int deleteByEtudiantId(@PathVariable Long id) {
        return etudiantService.deleteEtudiantById(id);
    }

    @GetMapping("/login/{login}/password/{password}")
    public Object findByCritere(@PathVariable String login, @PathVariable String password) {
        return etudiantService.findByCritere(login, password);
    }


}
