package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.Paiement;
import ma.learn.quiz.service.impl.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/etudiant/paiement")
public class PaiementEtudiantRest {


    @GetMapping("/")
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }


    @Autowired
    private PaiementService paiementService;
}
