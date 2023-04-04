package ma.learn.quiz.dao;


import ma.learn.quiz.bean.PackStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackStudentDao extends JpaRepository<PackStudent, Long> {

    PackStudent findPackStudentByCode(String code);

    PackStudent findPackStudentByPricePrice(Double prix);

    List<PackStudent> findPackStudentByForGroupe(boolean forgroupe);

    PackStudent findPackStudentByLibelle(String libelle);

    List<PackStudent> findByTotalStudents(int totalStudents);

    List<PackStudent> findByLevelId(Long id);

    int deleteByCode(String code);

    int deleteByForGroupe(boolean forGroupe);
}
