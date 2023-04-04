package ma.learn.quiz.service.impl;


import ma.learn.quiz.bean.CategorieSection;
import ma.learn.quiz.bean.SuperCategorieSection;
import ma.learn.quiz.dao.CategorieSectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategorieSectionService {

    @Autowired
    private SectionService sectionService;
    @Autowired
    private CategorieSectionDao categorieSectionDao;
    @Autowired
    private SuperCategorieSectionService superCategorieSectionService;

    public CategorieSection findCategorieSectionById(Long id) {
        return categorieSectionDao.findCategorieSectionById(id);
    }

    public int save(CategorieSection categorieSection) {
        if (findByCode(categorieSection.getCode()) != null) {
            return -1;
        }
        SuperCategorieSection superCategorieSection = superCategorieSectionService.findByCode(categorieSection.getSuperCategorieSection().getCode());

        if (superCategorieSection == null) return -2;
        else {
            categorieSection.setSuperCategorieSection(superCategorieSection);
            categorieSectionDao.save(categorieSection);
            return 1;
        }

    }

    public List<CategorieSection> findByLibelle(String libelle) {
        return categorieSectionDao.findByLibelle(libelle);
    }

    public void update(CategorieSection categorieSection) {
        categorieSectionDao.save(categorieSection);
    }

    public CategorieSection findByCode(String code) {
        return categorieSectionDao.findByCode(code);
    }

    public List<CategorieSection> findAll() {
        return categorieSectionDao.findAll();
    }

    @Transactional
    public int deleteByCode(String code) {
        int R1 = sectionService.deleteByCategorieSectionCode(code);
        int rsultat1 = categorieSectionDao.deleteByCode(code);
        return R1 + rsultat1;
    }

    @Transactional
    public int deleteBySuperCategorieSectionCode(String code) {
        return categorieSectionDao.deleteBySuperCategorieSectionCode(code);
    }


}
