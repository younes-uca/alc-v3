package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.HomeWork;
import ma.learn.quiz.bean.HomeWorkQuestion;
import ma.learn.quiz.bean.HoweWorkQSTReponse;
import ma.learn.quiz.bean.TypeDeQuestion;
import ma.learn.quiz.dao.HomeWorkQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HomeWorkQuestionService {

    @Autowired
    private HomeWorkQuestionDao homeWorkQuestionDao;
    @Autowired
    private TypeDeQuestionService typeDeQuestionService;
    @Autowired
    private ReponseService reponseService;
    @Autowired
    private HomeWorkQSTReponseService homeWorkQSTReponseService;

    public List<HomeWorkQuestion> findHomeWorkQuestionByHomeWorkId(Long id) {
        return homeWorkQuestionDao.findHomeWorkQuestionByHomeWorkIdOrderByNumero(id);
    }


    public HomeWorkQuestion findHomeWorkQuestionById(Long id) {
        return homeWorkQuestionDao.findHomeWorkQuestionById(id);
    }

    public int deleteByRef(String ref) {
        return homeWorkQuestionDao.deleteByRef(ref);
    }

    @Transactional
    public int deleteHomeWorkQuestionById(Long id) {
        this.reponseService.deleteAllByQuestionId(id);
        return homeWorkQuestionDao.deleteHomeWorkQuestionById(id);
    }

    public int deleteByHomeWork(HomeWork homeWork) {
        return homeWorkQuestionDao.deleteByHomeWork(homeWork);
    }

    public void saveHomeWorkQuestion(HomeWork homeWork, List<HomeWorkQuestion> questions) {
        for (HomeWorkQuestion question : questions) {
            addQstHomeWork(homeWork, question);
        }
    }

    public void addQstHomeWork(HomeWork homeWork, HomeWorkQuestion question) {
        List<HoweWorkQSTReponse> reponses = question.getReponses();
        question.setHomeWork(homeWork);
        TypeDeQuestion typeDeQuestion = typeDeQuestionService.findByRef(question.getTypeDeQuestion().getRef());
        question.setTypeDeQuestion(typeDeQuestion);
        typeDeQuestionService.update(typeDeQuestion);
        question.setTypeDeQuestion(typeDeQuestion);
        System.out.println("==============================");
        System.out.println(question.getId());
        System.out.println(question.getLibelle());
        if (question.getId() != null) {
            HomeWorkQuestion quest = this.findHomeWorkQuestionById(question.getId());
            quest.setLibelle(question.getLibelle());
            quest.setPointReponseJuste(question.getPointReponseJuste());
            quest.setPointReponsefausse(question.getPointReponsefausse());
            quest.setNumero(question.getNumero());
            quest.setRef(question.getRef());
            quest.setHomeWork(question.getHomeWork());
            quest.setTypeDeQuestion(typeDeQuestion);
            question = homeWorkQuestionDao.save(quest);
        } else {
            question = homeWorkQuestionDao.save(question);
        }
        if (reponses != null) {
            homeWorkQSTReponseService.save(question, reponses);
        }
    }
}
