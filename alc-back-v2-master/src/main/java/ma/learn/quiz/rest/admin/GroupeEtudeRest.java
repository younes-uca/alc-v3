package ma.learn.quiz.rest.admin;


import ma.learn.quiz.bean.GroupeEtude;
import ma.learn.quiz.service.impl.GroupeEtudeService;
import ma.learn.quiz.service.vo.GroupeEtudeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/groupeEtude")
public class GroupeEtudeRest {
    @Autowired
    private GroupeEtudeService groupeEtudeService;
    @PostMapping("/")
    public int save( @RequestBody GroupeEtude groupeEtude) {
        return groupeEtudeService.save(groupeEtude);
    }
    @GetMapping("/libelle/{libelle}")
    public GroupeEtude findByLibelle( @PathVariable String libelle) {
        return groupeEtudeService.findByLibelle(libelle);
    }
    @DeleteMapping("/libelle/{libelle}")
    public int deleteByLibelle  (@PathVariable String libelle) {
        return groupeEtudeService.deleteByLibelle(libelle);
    }
    @GetMapping("/")
    public List<GroupeEtude> findAll() {
        return groupeEtudeService.findAll();
    }
    @PutMapping("/")
    public int update( @RequestBody GroupeEtude groupeEtude) {
        return groupeEtudeService.update(groupeEtude);
    }

    @GetMapping("/id/{id}")
    public Optional<GroupeEtude> findById(@PathVariable Long id) {
        return groupeEtudeService.findById(id);
    }
    @PostMapping("/delete-multiple-by-id")
    public int deleteGroupeEtudeById( @RequestBody List<GroupeEtude> groupeEtude) {
        return groupeEtudeService.deleteGroupeEtudeById(groupeEtude);
    }
    @PostMapping("/allByCriteria")
    public List<GroupeEtude> findAllByCriteria( @RequestBody GroupeEtudeVo groupeEtudeVo) {
        return groupeEtudeService.findAllByCriteria(groupeEtudeVo);
    }
}
