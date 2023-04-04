package ma.learn.quiz.service.impl;


import ma.learn.quiz.bean.Question;
import ma.learn.quiz.bean.Reponse;
import ma.learn.quiz.dao.ReponseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReponseService {

    public List<Reponse> findByQuestionId(Long id) {
        return reponseDao.findByQuestionId(id);
    }

    @Transactional
    public int deleteByQuestionId(Long id) {
        return reponseDao.deleteByQuestionId(id);
    }

    @Transactional
    public void deleteById(Long id) {
        reponseDao.deleteById(id);
    }

    @Autowired
    private ReponseDao reponseDao;


    public Optional<Reponse> findById(Long id) {
        return reponseDao.findById(id);
    }


    public List<Reponse> findByEtatReponse(String etatReponse) {
        return reponseDao.findByEtatReponse(etatReponse);
    }

    @Autowired
    private EntityManager entityManager;

    public List<Reponse> findByQuestionNumero(Long numero) {
        return reponseDao.findByQuestionNumero(numero);
    }

    @Autowired
    private QuestionService questionService;

    public Reponse findByRef(String ref) {
        return reponseDao.findByRef(ref);
    }


    @Transactional
    public int deleteByRef(String ref) {
        return reponseDao.deleteByRef(ref);
    }

    public Reponse findReponseById(Long id) {
        return reponseDao.findReponseById(id);
    }

    public List<Reponse> findAll() {
        return reponseDao.findAll();
    }

    public int save(Reponse reponse) {
        if (findByRef(reponse.getRef()) != null) {
            return -1;
        }
        Question question = questionService.findByNumero(reponse.getQuestion().getNumero());
        reponse.setQuestion(question);
        if (question == null) {
            return -2;
        } else {
            questionService.update(question);
            reponseDao.save(reponse);
            return 1;

        }
    }

    public Reponse saveAnswer(Reponse reponse) {
        return reponseDao.save(reponse);
    }

    public List<Reponse> findByCriterial(Long id) {
        String query = "Select r FROM Reponse r WHERE r.question.id='" + id + "' And r.etatReponse = 'true'";
        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    public int deleteByQuestionRef(String ref) {
        return reponseDao.deleteByQuestionRef(ref);
    }


    public List<Reponse> save(Question question, List<Reponse> reponses) {
        List<Reponse> reponseList = new ArrayList<>();
        for (Reponse reponse : reponses) {
            reponse.setQuestion(question);
            reponse = reponseDao.save(reponse);
            reponseList.add(reponse);
        }
        return reponseList;
    }

    @Transactional
    public int deleteByQuestionQuizRef(String ref) {
        return reponseDao.deleteByQuestionQuizRef(ref);
    }

    @Transactional
    public void deleteAllByQuestionId(Long id) {
        reponseDao.deleteAllByQuestionId(id);
    }
}
