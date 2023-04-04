package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.LevelTestConfiguration;
import ma.learn.quiz.dao.LevelTestConfigurationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelTestConfigurationService {
    @Autowired
    private LevelTestConfigurationDao levelTestConfigurationDao;


    public List<LevelTestConfiguration> findAll() {
        return levelTestConfigurationDao.findAll();
    }

    public LevelTestConfiguration save(LevelTestConfiguration entity) {
        return levelTestConfigurationDao.save(entity);
    }

    public Optional<LevelTestConfiguration> findById(Long aLong) {
        return levelTestConfigurationDao.findById(aLong);
    }
}
