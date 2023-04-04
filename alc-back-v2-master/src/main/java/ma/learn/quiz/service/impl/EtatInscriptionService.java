package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.EtatInscription;
import ma.learn.quiz.dao.EtatInscriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class EtatInscriptionService {
    @Autowired
    public EtatInscriptionDao etatInscriptionDao;


    public EtatInscription findEtatInscriptionById(Long id) {
        return etatInscriptionDao.findEtatInscriptionById(id);
    }

    public List<EtatInscription> findAll() {
        return etatInscriptionDao.findAll();
    }

    @Transactional
    public int deleteByRef(String ref) {
        return etatInscriptionDao.deleteByRef(ref);
    }


    public EtatInscription findByRef(String ref) {
        return etatInscriptionDao.findByRef(ref);
    }

    public int save(EtatInscription etatInscription) {
        if (findByRef(etatInscription.getRef()) != null) {
            return -1;
        } else {
            etatInscriptionDao.save(etatInscription);
            return 1;
        }

    }

}
