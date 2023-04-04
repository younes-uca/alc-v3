package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.GroupeEtudiant;
import ma.learn.quiz.service.impl.GroupeEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prof/groupeEtudiant")
public class GroupeEtudiantProfRest {

    @GetMapping("/prof/id/{id}")
    public List<GroupeEtudiant> findGroupeEtudiantByProfId(@PathVariable Long id) {
        return groupeEtudiantService.findGroupeEtudiantByProfId(id);
    }


    @Autowired
    private GroupeEtudiantService groupeEtudiantService;
}
