package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.bean.Inscription;
import ma.learn.quiz.service.impl.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/inscription")
public class InscriptionAdminRest {
    @Autowired
    public InscriptionService inscriptionService;

    @PostMapping("/")
    public Etudiant save(@RequestBody Inscription inscriptionetudiant) throws Exception {
        return inscriptionService.save(inscriptionetudiant);
    }

    @GetMapping("/")
    public List<Inscription> findAll() {
        return inscriptionService.findAllByOrderByIdDesc();
    }


    @GetMapping("/etat/libelle/{libelle}")
    public List<Inscription> findAllByEtatInscriptionLibelle(@PathVariable String libelle) {
        return inscriptionService.findAllByEtatInscriptionLibelle(libelle);
    }

    @PostMapping("/search")
    public List<Inscription> findByCriteria(@RequestBody Inscription inscrit) {
        return inscriptionService.findByCriteria(inscrit);
    }

    @GetMapping("/numeroInscription/{numeroInscription}")
    public Inscription findByNumeroInscription(@PathVariable int numeroInscription) {
        return inscriptionService.findByNumeroInscription(numeroInscription);
    }


    @PostMapping("/delete-multiple-by-id")
    public int deleteInscriptionById(@RequestBody List<Inscription> inscription) {
        return inscriptionService.deleteInscriptionById(inscription);
    }

    @DeleteMapping("/id/{id}")
    public int deleteInscriptionById(@PathVariable Long id) {
        return inscriptionService.deleteInscriptionById(id);
    }

    @PutMapping("/")
    public int valider(@RequestBody Inscription inscription) {
        return inscriptionService.valider(inscription);
    }


}
