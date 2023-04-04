package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.TrancheHoraireProf;
import ma.learn.quiz.service.impl.TrancheHoraireProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/work-hours")
public class TrancheHoraireProfRest {
    @Autowired
    private TrancheHoraireProfService trancheHoraireProfService;

    @GetMapping("/id/{id}")
    public List<TrancheHoraireProf> findByProfId(@PathVariable Long id) {
        return trancheHoraireProfService.findByProfId(id);
    }

    @GetMapping("/")
    public List<TrancheHoraireProf> findAll() {
        return trancheHoraireProfService.findAll();
    }

    @PostMapping("/")
    public TrancheHoraireProf edit(@RequestBody TrancheHoraireProf trancheHoraireProf) {
        return trancheHoraireProfService.edit(trancheHoraireProf);
    }

    @DeleteMapping("/id/{id}")
    public int deleteTrancheHoraireProfById(@PathVariable Long id) {
        return trancheHoraireProfService.deleteTrancheHoraireProfById(id);
    }
}
