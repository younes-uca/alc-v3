package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.SuperCategorieSection;
import ma.learn.quiz.dao.SuperCategorieSectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SuperCategorieSectionService {
    @Autowired
    private SuperCategorieSectionDao superCategorieSectionDao;
    @Autowired
    private CategorieSectionService categorieSectionService;
    @Autowired
    private SectionService sectionService;


    public List<SuperCategorieSection> findByLibelle(String libelle) {
        return superCategorieSectionDao.findByLibelle(libelle);
    }

    public SuperCategorieSection findByCode(String code) {
        return superCategorieSectionDao.findByCode(code);
    }

    @Transactional
    public int deleteByCode(String code) {
        int r1 = sectionService.deleteByCategorieSectionCode(code);
        int r2 = categorieSectionService.deleteBySuperCategorieSectionCode(code);
        int r3 = superCategorieSectionDao.deleteByCode(code);
        return r1 + r2 + r3;
    }

    public int save(SuperCategorieSection superCategorieSection) {
        if (findByCode(superCategorieSection.getCode()) != null) {
            return -1;
        } else {
            superCategorieSectionDao.save(superCategorieSection);
            return 1;
        }
    }

    public List<SuperCategorieSection> findAll() {
        return superCategorieSectionDao.findAll();
    }

    public void update(SuperCategorieSection superCategorieSection) {
        superCategorieSectionDao.save(superCategorieSection);
    }

    public void deleteAll() {
        superCategorieSectionDao.deleteAll();
    }

}
