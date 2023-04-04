package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.ClassAverageBonus;
import ma.learn.quiz.bean.ClassAverageBonusProf;
import ma.learn.quiz.bean.Prof;
import ma.learn.quiz.dao.ClassAverageBonusProfDao;
import ma.learn.quiz.dao.SalaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClassAverageBonusProfService {
    @Autowired
    private ClassAverageBonusProfDao classAverageBonusProfDao;
    @Autowired
    private ProfService profService;
    @Autowired
    private ClassAverageBonusService classAverageBonusService;

    @Autowired
    private SalaryService salaryService;
    @Autowired
    private SalaryDao salaryDao;

    public List<ClassAverageBonusProf> findClassAverageBonusProfByProfId(Long id) {
        return classAverageBonusProfDao.findClassAverageBonusProfByProfId(id);
    }

    public List<ClassAverageBonusProf> findClassAverageBonusProfByMoisAndAnneeAndProfId(int mois, int annee, Long idprof) {
        return classAverageBonusProfDao.findClassAverageBonusProfByMoisAndAnneeAndProfId(mois, annee, idprof);
    }

    public BigDecimal findMontantClassAverageBonusProfByMoisAndAnneeAndProfId(int mois, int annee, Long idprof) {
        List<ClassAverageBonusProf> classAverageBonusProfList = findClassAverageBonusProfByMoisAndAnneeAndProfId(mois, annee, idprof);
        BigDecimal totalBonus = new BigDecimal(0);
        for (ClassAverageBonusProf classAverageBonusProf : classAverageBonusProfList) {
            totalBonus = totalBonus.add(classAverageBonusProf.getClassAverageBonus().getPrix());
        }
        return totalBonus;
    }

    public List<ClassAverageBonusProf> findAll() {
        return classAverageBonusProfDao.findAll();
    }

    public int save(Long idClassAverageBonus, Long idprof) {
        Prof prof = profService.findProfById(idprof);
        ClassAverageBonus classAverageBonus = classAverageBonusService.findClassAverageBonusById(idClassAverageBonus);
        if (prof == null) {
            return -1;
        } else {
            if (classAverageBonus == null) {
                return -2;
            } else {
                ClassAverageBonusProf classAverageBonusProf = new ClassAverageBonusProf();
                classAverageBonusProf.setProf(prof);
                classAverageBonusProf.setClassAverageBonus(classAverageBonus);
                classAverageBonusProfDao.save(classAverageBonusProf);
                return 1;
            }
        }
    }

    public ClassAverageBonusProf findClassAverageBonusProfBySalaryId(Long id) {
        return classAverageBonusProfDao.findClassAverageBonusProfBySalaryId(id);
    }

    public ClassAverageBonusProf findClassAverageBonusProfByProfIdAndSalaryId(Long idprof, Long idsalary) {
        return classAverageBonusProfDao.findClassAverageBonusProfByProfIdAndSalaryId(idprof, idsalary);
    }
}
