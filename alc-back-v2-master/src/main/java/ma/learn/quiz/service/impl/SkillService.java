package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Skill;
import ma.learn.quiz.dao.SkillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillDao skillDao;

    public List<Skill> findAll() {
        return skillDao.findAll();
    }

    public int save(Skill skill) {
        skillDao.save(skill);
        return 1;
    }


    public int update(Skill skill) {
        skillDao.save(skill);
        return 1;
    }

    @Transactional
    public int deleteByLibelle(String libelle) {
        return skillDao.deleteByLibelle(libelle);
    }
}
