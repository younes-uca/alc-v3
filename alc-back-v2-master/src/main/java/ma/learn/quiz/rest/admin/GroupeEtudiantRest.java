package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.GroupeEtudiant;
import ma.learn.quiz.bean.GroupeEtudiantDetail;
import ma.learn.quiz.service.impl.GroupeEtudiantDetailService;
import ma.learn.quiz.service.impl.GroupeEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/groupeEtudiant")
public class GroupeEtudiantRest {
    @Autowired
    private GroupeEtudiantService groupeEtudiantService;
    @Autowired
    private GroupeEtudiantDetailService groupeEtudiantDetailService;

    @GetMapping("/groupeProf/{id}")
    public List<GroupeEtudiant> findGroupeEtudiantByProfId(@PathVariable Long id) {
        return groupeEtudiantService.findGroupeEtudiantByProfId(id);
    }

    @PostMapping("/")
    public GroupeEtudiant save(@RequestBody GroupeEtudiant groupeEtudiant) throws Exception {
        return groupeEtudiantService.save(groupeEtudiant);
    }

    @GetMapping("/")
    public List<GroupeEtudiant> findAll() {
        return groupeEtudiantService.findAll();
    }

    @GetMapping("/id/{id}")
    public List<GroupeEtudiantDetail> findByGroupeEtudiantId(@PathVariable Long id) {
        return groupeEtudiantDetailService.findByGroupeEtudiantId(id);
    }

    @DeleteMapping("/id/{id}")
    public int deleteGroupeEtudiantById(@PathVariable Long id) {
        return groupeEtudiantService.deleteGroupeEtudiantById(id);
    }

    @PostMapping("/delete-multiple-by-id")
    public int deleteGroupeEtudeById(@RequestBody List<GroupeEtudiant> groupeEtudiant) {
        return groupeEtudiantService.deleteGroupeEtudiantById(groupeEtudiant);
    }

    @PutMapping("/")
    public int update(@RequestBody GroupeEtudiant groupeEtudiant) {
        return groupeEtudiantService.update(groupeEtudiant);
    }

    @GetMapping("/libelle/{libelle}/nombrePlacevide/{nombrePlacevide}")
    public List<GroupeEtudiant> findByParcoursIdAndNombrePlacevideGreaterThan(Long id, Long nombrePlacevide) {
        return groupeEtudiantService.findByParcoursIdAndNombrePlacevideGreaterThan(id, nombrePlacevide);
    }

    @PostMapping("/search/")
    public List<GroupeEtudiant> findByCriteria(@RequestBody GroupeEtudiant groupeEtudiant) {
        return groupeEtudiantService.findByCriteria(groupeEtudiant);
    }

}
