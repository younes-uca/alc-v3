package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.ReclamationProf;
import ma.learn.quiz.service.impl.ReclamationProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/prof/reclamationProfProf")
public class ReclamationProfProfRest {
    @Autowired
    private ReclamationProfService reclamationProfService;

    @GetMapping("/")
    public List<ReclamationProf> findAll() {
        return reclamationProfService.findAll();
    }

    @PostMapping("/")
    public int saveReclamationProf(@RequestBody ReclamationProf reclamationProf) {
        return reclamationProfService.saveReclamationProf(reclamationProf);
    }

    @GetMapping("/{id}")

    public List<ReclamationProf> findReclamationProfByProfId(Long id) {
        return reclamationProfService.findReclamationProfByProfId(id);
    }

    @GetMapping("/{id}/{idprof}")

    public ReclamationProf findReclamationProfByIdAndProfId(@PathVariable Long id, @PathVariable Long idprof) {
        return reclamationProfService.findReclamationProfByIdAndProfId(id, idprof);
    }
}
