package ma.learn.quiz.service.impl;


import ma.learn.quiz.bean.CategorieSection;
import ma.learn.quiz.bean.Cours;
import ma.learn.quiz.bean.Section;
import ma.learn.quiz.bean.SectionItem;
import ma.learn.quiz.dao.SectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService extends AbstractService {

    @Autowired
    public ParcoursService parcoursService;
    @Autowired
    public SectionDao sectionDao;
    @Autowired
    public CoursService coursService;
    @Autowired
    public CategorieSectionService categorieSectionService;
    @Autowired
    public EntityManager entityManager;
    @Autowired
    private VocabularyService vocabularyService;

    public Section findByCoursIdAndCategorieSectionLibelle(Long id, String libelle) {
        return sectionDao.findByCoursIdAndCategorieSectionLibelle(id, libelle);
    }


    public int deleteByCours(Cours cours) {
        return sectionDao.deleteByCours(cours);
    }


    public Section findByCoursIdAndNumeroOrder(Long id, int numeroOrder) {
        return sectionDao.findByCoursIdAndNumeroOrder(id, numeroOrder);
    }

    public List<Section> findByCoursIdOrderByNumeroOrder(Long id) {
        return sectionDao.findByCoursIdOrderByNumeroOrder(id);
    }

    public List<Section> findByCours(Cours cours) {
        return sectionDao.findByCours(cours);
    }


    public List<Section> findByLibelle(String libelle) {
        return sectionDao.findByLibelle(libelle);
    }


    public List<Section> findByCategorieSectionLibelle(String libelle) {
        return sectionDao.findByCategorieSectionLibelle(libelle);
    }

    @Transactional
    public int deleteSectionById(List<Section> sections) {
        int res = 0;
        for (int i = 0; i < sections.size(); i++) {
            res += deleteSectionById(sections.get(i).getId());
        }
        return res;
    }


    @Transactional
    public int deleteByCoursCode(String code) {
        return sectionDao.deleteByCoursCode(code);
    }


    public List<Section> findByCoursCode(String code) {
        return sectionDao.findByCoursCode(code);
    }


    @Cacheable(value = "sectionListByCourseId")
    public List<Section> findByCoursId(Long id) {
        return sectionDao.findByCoursIdOrderByNumeroOrder(id);
    }

    public List<Section> findByCriteria(Long id) {
        String query = "SELECT e FROM Section e WHERE e.cours.id='" + id + "'ORDER BY e.numeroOrder ASC";
        return entityManager.createQuery(query).getResultList();
    }


    public List<Section> search(Section section) {
        String query = this.init("Section");
        if (section.getCours().getParcours() != null) {
            query += this.addCriteria("cours.parcours.id", section.getCours().getParcours().getId(), "LIKE");
        }
        if (section.getCours() != null) {
            query += this.addCriteria("cours.id", section.getCours().getId(), "LIKE");
        }

        if (section.getCategorieSection() != null) {
            query += this.addCriteria("categorieSection.libelle", section.getCategorieSection().getLibelle(), "LIKE");
        }
        if (section.getLibelle() != null) {
            query += this.addCriteria("libelle", section.getLibelle(), "LIKE");
        }
        System.out.println("query = " + query.length());
        return entityManager.createQuery(query).getResultList();
    }


    public List<Section> findByCategorieSectionCode(String code) {
        return sectionDao.findByCategorieSectionCode(code);
    }

    public int deleteByCategorieSectionCode(String code) {
        return sectionDao.deleteByCategorieSectionCode(code);
    }

    public Section findByCode(String code) {
        return sectionDao.findByCode(code);
    }


    public int create(Section section) {
        sectionDao.save(section);

        return 1;
    }


    public Section save(Section section) throws Exception {
        Cours cours = coursService.findCoursById(section.getCours().getId());
        if (cours == null) {
            throw new Exception("Course is required");
        }
        CategorieSection categorieSection = categorieSectionService.findCategorieSectionById(section.getCategorieSection().getId());
        if (categorieSection == null) {
            throw new Exception("Category is required");
        }
        section.setCategorieSection(categorieSection);
        section.setCours(cours);
        section.setSessionCours(null);
        return sectionDao.save(section);
    }


    public List<Section> findAll() {
        return sectionDao.findAll();
    }

    public void transformurl() {
        List<Section> sections = findAll();
        int total = 0;
        int sum = 0;
        for (Section s : sections) {
            total++;
            System.out.println(s.toString());
            if (s.getUrlImage() != null) {
                if (!(s.getUrlImage().equals("")) || !(s.getUrlImage().isEmpty())) {
                    if (s.getUrlImage().startsWith("https://drive.google.com/file/d/")) {
                        String initialString = s.getUrlImage();
                        String stringExcerpt = "https://drive.google.com/uc?export=view&id=" + initialString.substring(32, 65);
                        s.setUrlImage(stringExcerpt);
                        sectionDao.save(s);
                        sum++;
                    }
                }
            }
        }
        System.out.println("tot sections = " + total + "et sum est " + sum);
    }

    public void transformurlvideo() {
        List<Section> sections = findAll();
        int total = 0;
        int sum = 0;
        for (Section s : sections) {
            total++;
            System.out.println(s.toString());
            if (s.getUrlVideo() != null) {
                if (!(s.getUrlVideo().equals("")) || !(s.getUrlVideo().isEmpty())) {
                    if (s.getUrlVideo().startsWith("https://www.youtube.com/watch")) {
                        String initialString = s.getUrlVideo();
                        String stringExcerpt = "https://www.youtube.com/embed/" + initialString.substring(32, 43);
                        s.setUrlVideo(stringExcerpt);
                        sectionDao.save(s);
                        sum++;
                    }
                }
            }
        }
    }


    public Section update(Section section) {

        Optional<Section> sections = sectionDao.findById(section.getId());
        Section LoadedSection = sections.get();
        Cours cours = coursService.findCoursById(section.getCours().getId());
        LoadedSection.setCours(cours);
        CategorieSection categorieSection = categorieSectionService.findCategorieSectionById(section.getCategorieSection().getId());
        LoadedSection.setCategorieSection(categorieSection);
        LoadedSection.setLibelle(section.getLibelle());
        LoadedSection.setContenu(section.getContenu());
        LoadedSection.setIndicationProf(section.getIndicationProf());
        LoadedSection.setQuestions(section.getQuestions());
        LoadedSection.setNumeroOrder(section.getNumeroOrder());
        LoadedSection.setQuestions(section.getQuestions());
        LoadedSection.setUrlVideo(section.getUrlVideo());
        LoadedSection.setUrlImage(section.getUrlImage());
        LoadedSection.setUrlImage2(section.getUrlImage2());
        LoadedSection.setUrlImage3(section.getUrlImage3());
        LoadedSection.setCode(section.getCode());
        if (section.getUrlImage() != null || section.getUrlVideo() != null) {
            LoadedSection.setUrl(1);
        }
        if (section.getContenu() != null) {
            LoadedSection.setContent(1);
        }
        coursService.update(section.getCours());
        return sectionDao.save(LoadedSection);

    }


    public int deleteByCoursId(Long id) {
        return sectionDao.deleteByCoursId(id);
    }


    @Transactional
    public int deleteSectionById(Long id) {
        int res2 = vocabularyService.deleteBySectionId(id);
        int res = sectionDao.deleteSectionById(id);
        return res2 + res;
    }


    public Section findSectionById(Long id) {
        return sectionDao.findSectionById(id);
    }

    public int setSectionItems(Long idSection, List<SectionItem> sectionItems) {
        Section section = findSectionById(idSection);

        if (section == null) {
            return -1;
        } else {
            if (sectionItems == null || sectionItems.isEmpty()) {
                return -2;
            } else {
                for (SectionItem item : sectionItems) {
                    item.setSection(section);
                }
                section.setSectionItems(sectionItems);
                sectionDao.save(section);
                return 1;
            }
        }
    }


}

	

