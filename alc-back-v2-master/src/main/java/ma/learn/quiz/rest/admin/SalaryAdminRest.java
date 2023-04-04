package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.Salary;
import ma.learn.quiz.service.impl.SalaryService;
import ma.learn.quiz.service.vo.SalaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/salary")
public class SalaryAdminRest {
    @Autowired
    private SalaryService salaryService;


    @GetMapping("/{mois}/{annee}/{id}")
    public Salary findSalaryByMoisAndAnneeAndProfId(@PathVariable int mois, @PathVariable int annee, @PathVariable Long id) {
        return salaryService.findSalaryByMoisAndAnneeAndProfId(mois, annee, id);
    }


    @GetMapping("/")

    public List<Salary> findAll() {
        return salaryService.findAll();
    }

    @PostMapping("/byCriteria")
    public List<Salary> findAllByCriteria(@RequestBody SalaryVo salaryVo) {
        return salaryService.findAllByCriteria(salaryVo);
    }
    @GetMapping("/etatPayer/{payer}")

    public List<Salary> findSalaryByPayer(@PathVariable boolean payer) {
        return salaryService.findSalaryByPayer(payer);
    }
}
