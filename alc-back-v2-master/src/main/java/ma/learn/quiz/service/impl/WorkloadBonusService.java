package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.WorkloadBonus;
import ma.learn.quiz.dao.WorkloadBonusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WorkloadBonusService {
    @Autowired
    private WorkloadBonusDao workloadBonusDao;


    public WorkloadBonus findWorkloadBonusById(Long id) {
        return workloadBonusDao.findWorkloadBonusById(id);
    }

    public WorkloadBonus findWorkloadBonusByNombreSession(int nombreSession) {
        return workloadBonusDao.findWorkloadBonusByNombreSession(nombreSession);
    }

    public List<WorkloadBonus> findAll() {
        return workloadBonusDao.findAll();
    }

    public int save(WorkloadBonus workloadBonus) {
        if (findWorkloadBonusByNombreSession(workloadBonus.getNombreSession()) != null) {
            return -1;
        } else {
            WorkloadBonus workloadBonus1 = new WorkloadBonus();
            workloadBonus1.setPrix(workloadBonus.getPrix());
            workloadBonus1.setNombreSession(workloadBonus.getNombreSession());
            workloadBonus1.setCode("Workload Bonus For " + workloadBonus.getNombreSession() + " Session");

            workloadBonusDao.save(workloadBonus1);
            return 1;
        }
    }

    public int update(WorkloadBonus workloadBonus, BigDecimal newprix) {
        WorkloadBonus workloadBonus1 = findWorkloadBonusById(workloadBonus.getId());
        if (workloadBonus1 == null) {
            return -1;
        } else {
            workloadBonus1.setPrix(newprix);
            workloadBonusDao.save(workloadBonus1);
            return 1;
        }
    }
}
