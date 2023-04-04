package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.Salary;
import ma.learn.quiz.service.impl.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/etudiant/salary")
public class SalaryEtudiantRest {
    @Autowired
    private SalaryService salaryService;


    @GetMapping("/{mois}/{annee}/{id}")
    public Salary findSalaryByMoisAndAnneeAndProfId(@PathVariable int mois, @PathVariable int annee, @PathVariable Long id) {
        return salaryService.findSalaryByMoisAndAnneeAndProfId(mois, annee, id);
    }
}
