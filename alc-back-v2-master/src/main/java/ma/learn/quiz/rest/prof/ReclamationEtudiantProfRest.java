package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.ReclamationEtudiant;
import ma.learn.quiz.exception.NotAnImageFileException;
import ma.learn.quiz.service.impl.ReclamationEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController

@RequestMapping("/prof/reclamationEtudiantAdmin")
public class ReclamationEtudiantProfRest {

    @Autowired
    private ReclamationEtudiantService reclamationEtudiantService;

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

    @PostMapping("/")
    public ReclamationEtudiant saveReclamationEtudiant(@RequestBody ReclamationEtudiant reclamationEtudiant) throws Exception {
        return reclamationEtudiantService.saveReclamationEtudiant(reclamationEtudiant);
    }
}
