package ma.learn.quiz.dao;


import ma.learn.quiz.bean.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursDao extends JpaRepository<Cours, Long> {
	List<Cours> findByLibelle(String libelle);
     Cours findByCode(String code);
     Cours findCoursById(Long id);
     int deleteByCode(String code);
     int deleteCoursById(Long id);
     List<Cours> findByParcoursIdOrderByNumeroOrder(Long id);
     int deleteByParcoursCode(String code);
     int deleteByParcoursId(Long id);
     Cours findCoursByLibelleAndParcoursId(String coursLibelle, Long idparcours);

}
