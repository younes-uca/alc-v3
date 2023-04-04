package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.GroupeEtudiantDetail;
import ma.learn.quiz.service.impl.GroupeEtudiantDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/etudiant/groupeEtudiantDetail/")
public class EtudiantGroupeEtudiantDetailRest {
    @Autowired
    private GroupeEtudiantDetailService groupeEtudiantDetailService;


    @GetMapping("groupeEtudiant/id/{id}")
    public List<GroupeEtudiantDetail> findByGroupeEtudiantId(@PathVariable Long id) {
        return groupeEtudiantDetailService.findByGroupeEtudiantId(id);
    }


    @GetMapping("etudiant/id/{id}")
    public List<GroupeEtudiantDetail> findByEtudiantId(@PathVariable Long id) {
        return groupeEtudiantDetailService.findByEtudiantId(id);
    }
}
