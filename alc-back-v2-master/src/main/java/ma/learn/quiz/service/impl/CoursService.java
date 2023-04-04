package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.CategorieSection;
import ma.learn.quiz.bean.Cours;
import ma.learn.quiz.bean.Parcours;
import ma.learn.quiz.bean.Section;
import ma.learn.quiz.dao.CoursDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoursService extends AbstractService {

    @Autowired
    public CategorieSectionService categorieSectionService;
    @Autowired
    private CoursDao coursDao;
    @Autowired
    private CoursService coursService;
    @Autowired
    public SectionService sectionService;
    @Autowired
    public ParcoursService parcoursService;
    @Autowired
    public EntityManager entityManager;

    public List<Cours> findByLibelle(String libelle) {
        return coursDao.findByLibelle(libelle);
    }

    public List<Cours> findByParcoursId(Long id) {
        return coursDao.findByParcoursIdOrderByNumeroOrder(id);
    }

    public List<Cours> findByCriteria(Long id) {
        if (!containsMalicous(id)) {
            String query = "SELECT e FROM Cours e WHERE e.parcours.id='" + id + "' ORDER BY e.numeroOrder ASC";
            return entityManager.createQuery(query).getResultList();
        } else return new ArrayList<>();

    }

    @Transactional
    public int deleteCoursById(List<Cours> courss) {
        int res = 0;
        for (int i = 0; i < courss.size(); i++) {
            res += deleteCoursById(courss.get(i).getId());
        }
        return res;
    }

    @Transactional
    public int deleteCoursById(Long id) {
        int deleteBySectionCode = sectionService.deleteByCoursId(id);
        int deleteByCode = coursDao.deleteCoursById(id);
        return deleteBySectionCode + deleteByCode;
    }

    public int deleteByParcoursId(Long id) {
        return coursDao.deleteByParcoursId(id);
    }

    public int init(Long id) {
        Cours cours = coursService.findCoursById(id);
        List<Section> sectionCours = sectionService.findByCoursId(id);
        int i = 0;
        List<CategorieSection> categorieSections = categorieSectionService.findAll();
        for (CategorieSection categorieSection : categorieSections) {
            if (findByCategorieSection(categorieSection, sectionCours) == null) {
                Section section = new Section();
                i = i + 1;
                section.setCategorieSection(categorieSection);
                section.setLibelle(categorieSection.getCode());
                section.setCours(cours);
                section.setNumeroOrder(categorieSection.getNumeroOrder());
                sectionService.create(section);
                System.out.println("saved");
            }
        }

        return 2;
    }

    private Section findByCategorieSection(CategorieSection categorieSection, List<Section> sectionCours) {
        if (sectionCours == null) return null;
        else {
            for (Section section : sectionCours) {
                if (section.getCategorieSection() != null && section.getCategorieSection().getId() == categorieSection.getId()) {
                    return section;
                }

            }
            return null;
        }

    }

    public Cours save(Cours cours) throws Exception {
        Parcours parcours = parcoursService.findParcoursByLibelle(cours.getParcours().getLibelle());
        if (parcours == null)
            throw new RuntimeException("Level with name " + cours.getParcours().getLibelle() + " not found");
        cours.setParcours(parcours);
        Cours c = coursDao.save(cours);
        List<CategorieSection> categorieSectionList = this.categorieSectionService.findAll();
        for (CategorieSection categorieSection : categorieSectionList) {
            Section section = new Section();
            section.setCours(c);
            section.setCategorieSection(categorieSection);
            section.setLibelle(categorieSection.getCode());
            section.setNumeroOrder(categorieSection.getNumeroOrder());
            sectionService.save(section);
        }
        return c;
    }

    public Cours create(Cours cours) {
        Cours cours1 = coursDao.save(cours);
        if (cours1.getNumeroOrder() != 0) {
            Section section = new Section();
            section.setCours(cours1);
            CategorieSection categorieSection = categorieSectionService.findByCode("Home Work Review");
            section.setCategorieSection(categorieSection);
            section.setNumeroOrder(categorieSection.getNumeroOrder());
            section.setLibelle(categorieSection.getLibelle());
            section.setCode(categorieSection.getCode());
            sectionService.create(section);
        }
        return cours1;
    }

    public List<Cours> findAll() {
        return coursDao.findAll();
    }

    @Transactional
    public void delete(Cours entity) {
        coursDao.delete(entity);
    }

    @Transactional
    public int deleteByParcoursCode(String code) {
        return coursDao.deleteByParcoursCode(code);
    }

    public Cours update(Cours cours) {
        List<Section> sections = sectionService.findByCours(cours);
        int nbFinalise = 0, nbEncours = 0, nbrLinkFinalise = 0, nbrLinkEncours = 0;
        for (Section section : sections) {
            if (section.getContenu() != null) {
                nbFinalise++;

            } else {
                nbEncours++;

            }
            if (section.getUrlImage() != null || section.getUrlVideo() != null) {
                nbrLinkFinalise++;

            } else {
                nbrLinkEncours++;
            }
        }
        Parcours parcours = null;
        try {
            parcours = parcoursService.findParcoursById(cours.getParcours().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        cours.setParcours(parcours);
        cours.setLibelle(cours.getLibelle());
        cours.setDescription(cours.getDescription());
        cours.setImage(cours.getImage());
        cours.setSections(cours.getSections());
        cours.setNumeroOrder(cours.getNumeroOrder());
        cours.setNombreLinkEnCours(nbrLinkEncours);
        cours.setNombreLinkFinalise(nbrLinkFinalise);
        cours.setNombreSectionEnCours(nbEncours);
        cours.setNombreSectionFinalise(nbFinalise);
        cours.setEtatCours(cours.getEtatCours());
        return coursDao.save(cours);

    }

    public Cours findCoursById(Long id) {
        return coursDao.findCoursById(id);
    }

}
