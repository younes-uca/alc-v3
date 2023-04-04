package ma.learn.quiz.dao;

import ma.learn.quiz.bean.SuperCategorieSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SuperCategorieSectionDao extends JpaRepository<SuperCategorieSection,Long> {
	SuperCategorieSection findByCode(String code); 
	List<SuperCategorieSection> findByLibelle(String libelle);
    int deleteByCode(String code);
}
