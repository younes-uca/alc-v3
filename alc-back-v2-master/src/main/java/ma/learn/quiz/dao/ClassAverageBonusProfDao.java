package ma.learn.quiz.dao;

import ma.learn.quiz.bean.ClassAverageBonusProf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassAverageBonusProfDao extends JpaRepository<ClassAverageBonusProf,Long> {
    List<ClassAverageBonusProf> findClassAverageBonusProfByProfId(Long id);
    List<ClassAverageBonusProf> findClassAverageBonusProfByMoisAndAnneeAndProfId(int mois,int annee,Long idprof);
    ClassAverageBonusProf findClassAverageBonusProfBySalaryId(Long id);
    ClassAverageBonusProf findClassAverageBonusProfByProfIdAndSalaryId(Long idprof,Long idsalary);

}
