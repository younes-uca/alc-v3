package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.Paiement;
import ma.learn.quiz.service.impl.PaiementService;
import ma.learn.quiz.service.vo.PaiementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/paiement")
public class PaiementAdminRest {
    @GetMapping("/{idSalary}")

    public int savePaiement(@PathVariable Long idSalary) {
        return paiementService.savePaiement(idSalary);
    }

    @GetMapping("/")
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }

    @PostMapping("/ByCriteria")
    public List<Paiement> findAllByCriteria(@RequestBody PaiementVo paiementVo) {
        return paiementService.findAllByCriteria(paiementVo);
    }

    @GetMapping("/detailspaiementByidProf/{mois}/{annee}/{profid}")

    public List<Paiement> findPaiementByMoisAndAnneeAndProfID(@PathVariable String mois, @PathVariable String annee, @PathVariable Long profid) {
        return paiementService.findPaiementByMoisAndAnneeAndProfID(mois, annee, profid);
    }


    @Autowired
    private PaiementService paiementService;
}
