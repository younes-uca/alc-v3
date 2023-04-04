package ma.learn.quiz.rest.etudiant;


import ma.learn.quiz.bean.Section;
import ma.learn.quiz.bean.SectionItem;
import ma.learn.quiz.service.impl.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant/section")
public class SectionEtudiantRest {
    @Autowired
    private SectionService sectionservice;

    @PostMapping("/delete-multiple-by-id")
    public int deleteSectionById(@RequestBody List<Section> sections) {
        return sectionservice.deleteSectionById(sections);
    }

    @PostMapping("/")
    public Section save(@RequestBody Section section) throws Exception {
        return sectionservice.save(section);
    }

    @GetMapping("/")
    public List<Section> findAll() {
        return sectionservice.findAll();
    }

    @GetMapping("/transform")
    public void transformurl() {
        sectionservice.transformurl();
    }

    @GetMapping("/transformvid")
    public void transformurlvideo() {
        sectionservice.transformurlvideo();
    }

    @DeleteMapping("/id/{id}")
    public int deleteSectionById(@PathVariable Long id) {
        return sectionservice.deleteSectionById(id);
    }

    @GetMapping("/code/{code}")
    public Section findByCode(@PathVariable String code) {
        return sectionservice.findByCode(code);
    }


    @PutMapping("/")
    public Section update(@RequestBody Section section) {
        return sectionservice.update(section);
    }


    @GetMapping("/cours/id/{id}")
    public List<Section> findByCoursId(@PathVariable Long id) {
        return sectionservice.findByCoursId(id);
    }

    @GetMapping("/order/id/{id}")
    public List<Section> findByCriteria(@PathVariable Long id) {
        return sectionservice.findByCriteria(id);
    }

    @GetMapping("/cours/id/{id}/categorie/libelle/{libelle}")
    public Section findByCoursIdAndCategorieSectionLibelle(@PathVariable Long id, @PathVariable String libelle) {
        return sectionservice.findByCoursIdAndCategorieSectionLibelle(id, libelle);
    }

    @GetMapping("/section/id/{id}")
    public Section findSectionById(@PathVariable Long id) {
        return sectionservice.findSectionById(id);
    }

    @GetMapping("/cours/id/{id}/numeroOrder/{numeroOrder}")
    public Section findByCoursIdAndNumeroOrder(@PathVariable Long id, @PathVariable int numeroOrder) {
        return sectionservice.findByCoursIdAndNumeroOrder(id, numeroOrder);
    }


    @GetMapping("/idsection/{id}")
    public List<SectionItem> findSectionItemsBySection(@PathVariable Long id) {
        Section section = sectionservice.findSectionById(id);
        return section.getSectionItems();
    }

    @PostMapping("/idSection/{id}")
    public int setSectionItems(@PathVariable Long id, @RequestBody List<SectionItem> sectionItems) {
        return sectionservice.setSectionItems(id, sectionItems);
    }


}
