package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Question;
import ma.learn.quiz.bean.QuizEtudiant;
import ma.learn.quiz.bean.Reponse;
import ma.learn.quiz.bean.ReponseEtudiant;
import ma.learn.quiz.dao.ReponseEtudiantDao;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Service
public class ReponseEtudiantService {

    @Transactional
    public int deleteByQuizEtudiantId(Long id) {
        return reponseEtudiantDao.deleteByQuizEtudiantId(id);
    }

    @Autowired
    private ReponseEtudiantDao reponseEtudiantDao;

    @Autowired
    private EntityManager entityManager;

    public List<ReponseEtudiant> findByCriteria(Long idQuizEtudiant, Long idQuestion) {
        String query = "SELECT r FROM ReponseEtudiant r WHERE r.quizEtudiant.id= '" + idQuizEtudiant + "' and r.question.id='" + idQuestion + "'";
        return entityManager.createQuery(query).getResultList();
    }

    public List<ReponseEtudiant> findByQuizEtudiantRef(String ref) {
        return reponseEtudiantDao.findByQuizEtudiantRef(ref);
    }

    @Autowired
    private QuizEtudiantService quizEtudiantService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ReponseService reponseService;
    @Autowired
    private EtudiantService etudiantService;

    public ReponseEtudiant findByRef(String ref) {
        return reponseEtudiantDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String Ref) {
        return reponseEtudiantDao.deleteByRef(Ref);
    }

    public List<ReponseEtudiant> findByReponseQuestionRef(String ref) {
        return reponseEtudiantDao.findByReponseQuestionRef(ref);
    }

    public List<ReponseEtudiant> findByQuizEtudiantEtudiantRef(String ref) {
        return reponseEtudiantDao.findByQuizEtudiantEtudiantRef(ref);
    }

    @Transactional
    public int deleteByReponseQuestionRef(String ref) {
        return reponseEtudiantDao.deleteByReponseQuestionRef(ref);
    }

    @Transactional
    public int deleteByQuizEtudiantEtudiantRef(String ref) {
        return reponseEtudiantDao.deleteByQuizEtudiantEtudiantRef(ref);
    }

    public ReponseEtudiant save(ReponseEtudiant reponseEtudiant) {
        Reponse reponse;
        if (reponseEtudiant.getReponse().getId() == null) {
            reponse = this.reponseService.saveAnswer(reponseEtudiant.getReponse());
        } else {
            reponse = this.reponseService.findReponseById(reponseEtudiant.getReponse().getId());
        }
        QuizEtudiant quizEtudiant = this.quizEtudiantService.findQuizEtudiantById(reponseEtudiant.getQuizEtudiant().getId());
        Question question = this.questionService.findQuestionById(reponseEtudiant.getQuestion().getId());
        reponseEtudiant.setReponse(reponse);
        reponseEtudiant.setQuizEtudiant(quizEtudiant);
        reponseEtudiant.setQuestion(question);
        reponseEtudiant.setRef(RandomStringUtils.randomAlphanumeric(3));
        return reponseEtudiantDao.save(reponseEtudiant);
    }

    public List<ReponseEtudiant> findAll() {
        return reponseEtudiantDao.findAll();
    }


    public List<ReponseEtudiant> findByQuizEtudiantEtudiantId(Long id) {
        return reponseEtudiantDao.findByQuizEtudiantEtudiantId(id);
    }

    public List<ReponseEtudiant> findByQuizEtudiantId(Long id) {
        return reponseEtudiantDao.findByQuizEtudiantIdOrderByQuestionNumero(id);
    }
}
