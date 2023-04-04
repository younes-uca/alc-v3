package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.ClassAverageBonus;
import ma.learn.quiz.service.impl.ClassAverageBonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/admin/classAverageBonus")
public class ClassAverageBonusAdminRest {
    @Autowired
    private ClassAverageBonusService classAverageBonusService;
@GetMapping("/{id}")
    public ClassAverageBonus findClassAverageBonusById(@PathVariable Long id) {
        return classAverageBonusService.findClassAverageBonusById(id);
    }
    @GetMapping("/")

    public List<ClassAverageBonus> findAll() {
        return classAverageBonusService.findAll();
    }
    @GetMapping("/{nombreSession}")

    public ClassAverageBonus findClassAverageBonusByNombreSession(@PathVariable int nombreSession) {
        return classAverageBonusService.findClassAverageBonusByNombreSession(nombreSession);
    }
    @PostMapping("/")

    public int save(@RequestBody ClassAverageBonus classAverageBonus) {
        return classAverageBonusService.save(classAverageBonus);
    }
    @PutMapping("/{newprix}")

    public int update(@RequestBody ClassAverageBonus classAverageBonus,@PathVariable BigDecimal newprix) {
        return classAverageBonusService.update(classAverageBonus, newprix);
    }
}
