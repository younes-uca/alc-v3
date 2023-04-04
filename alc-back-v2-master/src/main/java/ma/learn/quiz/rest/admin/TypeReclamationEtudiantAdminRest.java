package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.TypeReclamationEtudiant;
import ma.learn.quiz.service.impl.TypeReclamationEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/typeReclamationEtudiantAdmin")
public class TypeReclamationEtudiantAdminRest {
    @Autowired
    private TypeReclamationEtudiantService typeReclamationEtudiantService;

    @GetMapping("/{code}")
    public TypeReclamationEtudiant findTypeReclamationEtudiantByCode(@PathVariable String code) {
        return typeReclamationEtudiantService.findTypeReclamationEtudiantByCode(code);
    }

    @DeleteMapping("/{id}")
    public int deleteTypeReclamationEtudiantById(@PathVariable Long id) {
        return typeReclamationEtudiantService.deleteTypeReclamationEtudiantById(id);
    }

    @PostMapping("/")
    public int saveTypeReclamationEtudiant(@RequestBody TypeReclamationEtudiant typeReclamationEtudiant) {
        return typeReclamationEtudiantService.saveTypeReclamationEtudiant(typeReclamationEtudiant);
    }

    @GetMapping("/")
    public List<TypeReclamationEtudiant> findAll() {
        return typeReclamationEtudiantService.findAll();
    }
}
