package ma.learn.quiz.dao;


import ma.learn.quiz.bean.CategorieSection;
import ma.learn.quiz.bean.Cours;
import ma.learn.quiz.bean.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionDao extends JpaRepository<Section,Long>{
	List<Section> findByLibelle(String libelle);
	 Section findByCode(String code); 
     int deleteByCode(String code);
     List<Section> findByCoursCode(String code);
     List<Section> findByCoursId(Long id);
     Section findByCoursIdAndCategorieSectionLibelle(Long id,String libelle);
     Section findByCoursIdAndNumeroOrder(Long id,int numeroOrder);
     Section findSectionById(Long id);
     List<Section> findByCours(Cours cours);
     Section findSectionByCoursAndCategorieSection(Cours cours,CategorieSection categorieSection);
     Section findSectionByCours(Cours cours);
     int deleteByCoursCode(String code);
     int deleteByCoursId(Long id);
     int deleteByCours(Cours cours);
     List<Section> findByCategorieSectionCode(String code); 
     int deleteByCategorieSectionCode(String code);
     List<Section> findByCategorieSectionLibelle(String libelle); 
     int deleteSectionById(Long id);
     Section findSectionByLibelleAndCoursId(String sectionLibelle, Long idcours);

     List<Section> findByCoursIdOrderByNumeroOrder(Long id);
}
 


