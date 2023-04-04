package ma.learn.quiz.dao;

import ma.learn.quiz.bean.WorkloadBonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkloadBonusDao extends JpaRepository<WorkloadBonus,Long> {
 WorkloadBonus findWorkloadBonusById(Long id);
  WorkloadBonus findWorkloadBonusByNombreSession(int nombreSession);

 }
