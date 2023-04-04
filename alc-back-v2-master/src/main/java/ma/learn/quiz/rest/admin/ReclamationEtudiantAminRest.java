package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.ReclamationEtudiant;
import ma.learn.quiz.exception.NotAnImageFileException;
import ma.learn.quiz.service.impl.ReclamationEtudiantService;
import ma.learn.quiz.service.vo.ReclamationEtudiantVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/reclamationEtudiantAdmin")
public class ReclamationEtudiantAminRest {
    @Autowired
    private ReclamationEtudiantService reclamationEtudiantService;

    @PutMapping("/update/{dateTraitementforEtudiant}")

    public int reponseReclamationEtudiant(@RequestBody ReclamationEtudiant reclamationEtudiant, @PathVariable Date dateTraitementforEtudiant) {
        return reclamationEtudiantService.reponseReclamationEtudiant(reclamationEtudiant, dateTraitementforEtudiant);
    }


    @PutMapping("/update/all")
    public List<ReclamationEtudiant> updateAll(@RequestBody List<ReclamationEtudiant> reclamationEtudiants) {
        return reclamationEtudiantService.updateAll(reclamationEtudiants);
    }

    @PostMapping("/updateImg")
    public ReclamationEtudiant updateImg(@RequestParam("id") Long id, @RequestParam(value = "img") MultipartFile img) throws IOException, NotAnImageFileException {
        ReclamationEtudiant reclamationEtudiant = this.reclamationEtudiantService.findReclamationEtudiantById(id);
        return this.reclamationEtudiantService.saveReclamationImage(reclamationEtudiant, img);
    }


    @GetMapping("/")

    public List<ReclamationEtudiant> findAll() {
        return reclamationEtudiantService.findAll();
    }

    @GetMapping("/{id}")

    public ReclamationEtudiant findReclamationEtudiantById(@PathVariable Long id) {
        return reclamationEtudiantService.findReclamationEtudiantById(id);
    }

    @GetMapping("/{id}/{idetudiant}")
    public ReclamationEtudiant findReclamationEtudiantByIdAndEtudiantId(@PathVariable Long id, @PathVariable Long idetudiant) {
        return reclamationEtudiantService.findReclamationEtudiantByIdAndEtudiantId(id, idetudiant);
    }

    @PostMapping("/byCriteria")
    public List<ReclamationEtudiant> findAllByCriteria(@RequestBody ReclamationEtudiantVo reclamationEtudiantVo) {
        return reclamationEtudiantService.findAllByCriteria(reclamationEtudiantVo);
    }

    @GetMapping("/idReclamationEtudiant1/{idReclamationEtudiant1}")
    public int viewReclamationEtudiant(@PathVariable Long idReclamationEtudiant1) {
        return reclamationEtudiantService.viewReclamationEtudiant(idReclamationEtudiant1);
    }
}
