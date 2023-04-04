package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.WorkloadBonusProf;
import ma.learn.quiz.service.impl.WorkloadBonusProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/prof/workloadBonusProf")
public class WorkloadBonusProfProfRest {
    @Autowired
    private WorkloadBonusProfService workloadBonusProfService;

    @GetMapping("/prof/mois/{mois}/annee/{annee}/idprof/{idprof}")
    public List<WorkloadBonusProf> findWorkloadBonusProfByMoisAndAnneeAndProfId(@PathVariable int mois, @PathVariable int annee, @PathVariable Long idprof) {
        return workloadBonusProfService.findWorkloadBonusProfByMoisAndAnneeAndProfId(mois, annee, idprof);
    }

    @GetMapping("/AllWorkloadBonusprof/mois/{mois}/annee/{annee}/idprof/{idprof}")

    public BigDecimal findAllWorkloadBonusProfByMoisAndAnneeAndProfId(@PathVariable int mois, @PathVariable int annee, @PathVariable Long idprof) {
        return workloadBonusProfService.findAllWorkloadBonusProfByMoisAndAnneeAndProfId(mois, annee, idprof);
    }

    @GetMapping("/id/{id}")

    public List<WorkloadBonusProf> findWorkloadBonusProfByProfId(@PathVariable Long id) {
        return workloadBonusProfService.findWorkloadBonusProfByProfId(id);
    }

    @GetMapping("/")

    public List<WorkloadBonusProf> findAll() {
        return workloadBonusProfService.findAll();
    }

    @GetMapping("/prof/{idprof}/{idsalary}")

    public WorkloadBonusProf findWorkloadBonusProfByProfIdAndSalaryId(@PathVariable Long idprof, @PathVariable Long idsalary) {
        return workloadBonusProfService.findWorkloadBonusProfByProfIdAndSalaryId(idprof, idsalary);
    }
}
