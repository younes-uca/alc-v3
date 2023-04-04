package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.WorkloadBonus;
import ma.learn.quiz.service.impl.WorkloadBonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/admin/workloadBonus")
public class WorkloadBonusAdminRest {
    @Autowired
    private WorkloadBonusService workloadBonusService;

    @GetMapping("/{id}")
    public WorkloadBonus findWorkloadBonusById(@PathVariable Long id) {
        return workloadBonusService.findWorkloadBonusById(id);
    }

    @GetMapping("/")

    public List<WorkloadBonus> findAll() {
        return workloadBonusService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody WorkloadBonus workloadBonus) {
        return workloadBonusService.save(workloadBonus);
    }

    @PutMapping("/{newprix}")

    public int update(@RequestBody WorkloadBonus workloadBonus, @PathVariable BigDecimal newprix) {
        return workloadBonusService.update(workloadBonus, newprix);
    }
}
