package ma.learn.quiz.dao;


import ma.learn.quiz.bean.CategorieSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieSectionDao extends JpaRepository<CategorieSection, Long> {

    CategorieSection findByCode(String code);

    List<CategorieSection> findByLibelle(String libelle);

    int deleteByCode(String code);

    int deleteBySuperCategorieSectionCode(String code);

    CategorieSection findCategorieSectionById(Long id);

    CategorieSection findCategorieSectionByLibelleIgnoreCase(String libelle);


}



