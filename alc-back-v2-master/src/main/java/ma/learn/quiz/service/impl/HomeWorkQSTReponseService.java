package ma.learn.quiz.service.impl;


import ma.learn.quiz.bean.HomeWorkQuestion;
import ma.learn.quiz.bean.HoweWorkQSTReponse;
import ma.learn.quiz.dao.HomeWorkQSTReponseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeWorkQSTReponseService {

    @Autowired
    private HomeWorkQSTReponseDao homeWorkQSTReponseDao;

    public List<HoweWorkQSTReponse> findByHomeWorkQuestionId(Long id) {
        return homeWorkQSTReponseDao.findByHomeWorkQuestionId(id);
    }

    public List<HoweWorkQSTReponse> findByEtatReponse(String etatreponse) {
        return homeWorkQSTReponseDao.findByEtatReponse(etatreponse);
    }

    public HoweWorkQSTReponse findHoweWorkQSTReponseById(Long id) {
        return homeWorkQSTReponseDao.findHoweWorkQSTReponseById(id);
    }

    public int save(HomeWorkQuestion homeWorkQuestion, List<HoweWorkQSTReponse> reponses) {
        int i = 0;
        for (HoweWorkQSTReponse reponse : reponses) {
            if (reponse.getId() != null) {
                HoweWorkQSTReponse rps = this.findHoweWorkQSTReponseById(reponse.getId());
                rps.setLib(reponse.getLib());
                rps.setNumero(reponse.getNumero());
                rps.setEtatReponse(reponse.getEtatReponse());
                rps.setHomeWorkQuestion(homeWorkQuestion);
                homeWorkQSTReponseDao.save(rps);
            } else {
                reponse.setHomeWorkQuestion(homeWorkQuestion);
                homeWorkQSTReponseDao.save(reponse);
            }
            i++;
        }
        return i;
    }

    public HoweWorkQSTReponse update(HoweWorkQSTReponse reponse) {
        return this.homeWorkQSTReponseDao.save(reponse);
    }
}
