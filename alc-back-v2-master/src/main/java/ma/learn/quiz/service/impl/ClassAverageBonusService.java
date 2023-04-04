package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.ClassAverageBonus;
import ma.learn.quiz.dao.ClassAverageBonusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClassAverageBonusService {
    @Autowired
    private ClassAverageBonusDao classAverageBonusDao;

    public ClassAverageBonus findClassAverageBonusById(Long id) {
        return classAverageBonusDao.findClassAverageBonusById(id);
    }

    public List<ClassAverageBonus> findAll() {
        return classAverageBonusDao.findAll();
    }

    public ClassAverageBonus findClassAverageBonusByNombreSession(int nombreSession) {
        return classAverageBonusDao.findClassAverageBonusByNombreSession(nombreSession);
    }

    public int save(ClassAverageBonus classAverageBonus) {
        if (findClassAverageBonusByNombreSession(classAverageBonus.getNombreSession()) != null) {
            return -1;
        } else {
            ClassAverageBonus classAverageBonus1 = new ClassAverageBonus();
            classAverageBonus1.setPrix(classAverageBonus.getPrix());
            classAverageBonus1.setNombreSession(classAverageBonus.getNombreSession());
            classAverageBonus1.setCode("Class Average Bonus "+classAverageBonus.getNombreSession()+" Session");
            classAverageBonusDao.save(classAverageBonus1);
            return 1;
        }
    }

    public int update(ClassAverageBonus classAverageBonus, BigDecimal newprix) {
        ClassAverageBonus classAverageBonus1 = findClassAverageBonusById(classAverageBonus.getId());
        if (classAverageBonus1 == null) {
            return -1;
        } else {
            classAverageBonus1.setPrix(newprix);
            classAverageBonusDao.save(classAverageBonus1);
            return 1;
        }
    }
}
