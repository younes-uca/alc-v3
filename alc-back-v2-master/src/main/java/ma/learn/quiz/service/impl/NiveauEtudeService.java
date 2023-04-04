package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.NiveauEtude;
import ma.learn.quiz.dao.NiveauEtudeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NiveauEtudeService {
    @Autowired
    private NiveauEtudeDao niveauEtudeDao;

    public int save(NiveauEtude niveauEtude) {
        niveauEtudeDao.save(niveauEtude);
        return 1;
    }

    public List<NiveauEtude> findAll() {
        return niveauEtudeDao.findAll();
    }

    public int update(NiveauEtude niveauEtude) {
        niveauEtudeDao.save(niveauEtude);
        return 1;
    }

    @Transactional
    public int deleteByLibelle(String libelle) {
        return niveauEtudeDao.deleteByLibelle(libelle);
    }
}
