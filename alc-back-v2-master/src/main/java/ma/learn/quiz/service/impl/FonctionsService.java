package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Fonction;
import ma.learn.quiz.dao.FonctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FonctionsService {
    @Autowired
    private FonctionDao fonctionDao;

    public List<Fonction> findAll() {
        return fonctionDao.findAll();
    }
    public int save (Fonction fonction){
        fonctionDao.save(fonction);
        return 1;
    }
    public int update(Fonction fonction){
        fonctionDao.save(fonction);
        return 1;
    }
    @Transactional
    public int deleteByLibelle(String libelle) {
        return fonctionDao.deleteByLibelle(libelle);
    }
}
