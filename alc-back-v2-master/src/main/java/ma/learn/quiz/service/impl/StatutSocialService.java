package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.StatutSocial;
import ma.learn.quiz.dao.StatutSocialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StatutSocialService {
    @Autowired
    private StatutSocialDao statutSocialDao;

    public List<StatutSocial> findAll() {
        return statutSocialDao.findAll();
    }
    public int  save(StatutSocial statutSocial){

        statutSocialDao.save(statutSocial);
        return 1;

    }
    public int update(StatutSocial statutSocial){
        statutSocialDao.save(statutSocial);
        return 1;
    }
    @Transactional
    public int deleteByLibelle(String libelle) {
        return statutSocialDao.deleteByLibelle(libelle);
    }
}
