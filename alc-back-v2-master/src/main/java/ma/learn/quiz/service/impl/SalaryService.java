package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Salary;
import ma.learn.quiz.dao.SalaryDao;
import ma.learn.quiz.service.vo.SalaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SalaryService extends AbstractService {
    @Autowired
    private SalaryDao salaryDao;

    @Autowired
    private ProfService profService;
    @Autowired
    public EntityManager entityManager;

    public Salary findSalaryByMoisAndAnneeAndProfId(int mois, int annee, Long id) {
        return salaryDao.findSalaryByMoisAndAnneeAndProfId(mois, annee, id);
    }


    public List<Salary> findSalaryByProfId(Long idprof) {
        return salaryDao.findSalaryByProfId(idprof);
    }

    public List<Salary> findAll() {
        return salaryDao.findAll();
    }

    public Salary findSalaryByMoisAndAnnee(int mois, int annee) {
        return salaryDao.findSalaryByMoisAndAnnee(mois, annee);
    }


    public List<Salary> findAllByCriteria(SalaryVo salaryVo) {
        String query = this.init("Salary");
        if (salaryVo.getProf().getNom() != null) {
            query += this.addCriteria("prof.nom", salaryVo.getProf().getNom(), "LIKE");
        }
        if (salaryVo.getAnnee() != null) {
            query += this.addCriteria("annee", salaryVo.getAnnee(), "LIKE");
        }
        if (salaryVo.getMois() != null) {
            query += this.addCriteria("mois", salaryVo.getMois(), "LIKE");
        }
        if (salaryVo.getCode() != null) {
            query += this.addCriteria("code", salaryVo.getCode(), "LIKE");
        }
        if (salaryVo.getPayer() != null) {
            query += this.addCriteria("payer", salaryVo.getPayer());
        }

        System.out.println("query = " + query);
        return entityManager.createQuery(query).getResultList();
    }

    public List<Salary> findSalaryByPayer(boolean payer) {
        return salaryDao.findSalaryByPayer(payer);
    }
}
