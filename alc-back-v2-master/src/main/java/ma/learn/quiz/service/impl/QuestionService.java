package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Question;
import ma.learn.quiz.bean.Quiz;
import ma.learn.quiz.bean.Reponse;
import ma.learn.quiz.bean.TypeDeQuestion;
import ma.learn.quiz.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    public List<Question> findByQuizRef(String ref) {
        List<Question> questionList = questionDao.findByQuizRef(ref);
        for (Question value : questionList) {
            List<Reponse> reponseList = this.reponseService.findByQuestionId(value.getId());
            value.setReponses(reponseList);
        }
        for (Question question : questionList) {
            System.out.println(question.getReponses().size());
        }
        return questionList;
    }

    @Transactional
    public int deleteById(Question qst) {
        reponseService.deleteAllByQuestionId(qst.getId());
        questionDao.deleteById(qst.getId());
        return 1;
    }

    public Optional<Question> findById(Long id) {
        return questionDao.findById(id);
    }

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuizService quizService;
    @Autowired
    private ReponseService reponseService;
    @Autowired
    private TypeDeQuestionService typeDeQuestionService;
    @Autowired
    private EntityManager entityManager;

    public void update(Question question) {
        questionDao.save(question);
    }

    public Question findByRef(String ref) {
        return questionDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        int question = questionDao.deleteByRef(ref);
        int reponse = reponseService.deleteByQuestionRef(ref);
        return question + reponse;
    }

    public List<Question> findAll() {
        return questionDao.findAll();
    }

    public int save(Question question) {
        /*
         * if (findByRef(question.getRef()) != null) { return -1; }
         */
        Quiz quiz = quizService.findByRef(question.getQuiz().getRef());
        TypeDeQuestion typeDeQuestion = typeDeQuestionService.findByRef(question.getTypeDeQuestion().getRef());
        question.setQuiz(quiz);
        question.setTypeDeQuestion(typeDeQuestion);
        if (quiz == null) {
            return -2;
        }

        if (typeDeQuestion == null) {
            return -3;
        } else {
            quizService.update(quiz);
            typeDeQuestionService.update(typeDeQuestion);
            questionDao.save(question);
            if (question.getReponses() != null) {
                reponseService.save(question, question.getReponses());
            }
            return 1;
        }
    }

    public Question findQuestionById(Long Id) {
        return questionDao.findQuestionById(Id);
    }

    List<Question> questionList = new ArrayList<>();

    public List<Question> saveAll(Quiz quiz, List<Question> questions) {
        List<Question> questionList = new ArrayList<>();
        for (Question question : questions) {
            Question question2;
            question.setQuiz(quiz);
            TypeDeQuestion typeDeQuestion = typeDeQuestionService.findByRef(question.getTypeDeQuestion().getRef());
            question.setTypeDeQuestion(typeDeQuestion);
            typeDeQuestionService.update(typeDeQuestion);
            System.out.println(question.getLibelle());
            question2 = questionDao.save(question);
            if (question.getReponses() != null) {
                List<Reponse> reponseList = reponseService.save(question, question.getReponses());
                question2.setReponses(reponseList);
            }
            questionList.add(question2);
        }
        return questionList;
    }


    public Question findByNumero(Long numero) {
        return questionDao.findByNumero(numero);
    }

    public Object findByQuizAndNumero(String ref, Long numero) {
        String query = "SELECT q FROM Question q WHERE q.quiz.ref= '" + ref + "' and q.numero='" + numero + "'";
        return entityManager.createQuery(query).getSingleResult();
    }

    @Transactional
    public int deleteByQuizRef(String ref) {
        return questionDao.deleteByQuizRef(ref);
    }

    @Transactional
    public void deleteAllByQuizId(Long id) {
        this.questionDao.deleteAllByQuizId(id);
    }
}