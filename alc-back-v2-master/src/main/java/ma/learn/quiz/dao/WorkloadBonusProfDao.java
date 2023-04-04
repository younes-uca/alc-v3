package ma.learn.quiz.dao;

import ma.learn.quiz.bean.WorkloadBonusProf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkloadBonusProfDao extends JpaRepository<WorkloadBonusProf, Long> {
    List<WorkloadBonusProf> findWorkloadBonusProfByProfId(Long id);
    List<WorkloadBonusProf> findWorkloadBonusProfByMoisAndAnneeAndProfId(int mois,int annee,Long idprof);
    WorkloadBonusProf findWorkloadBonusProfBySalaryId(Long id);
    WorkloadBonusProf findWorkloadBonusProfByProfIdAndSalaryId(Long idprof,Long idsalary);
}
