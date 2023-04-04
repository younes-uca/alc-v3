package ma.learn.quiz.service.impl;


import ma.learn.quiz.bean.Question;
import ma.learn.quiz.bean.Quiz;
import ma.learn.quiz.bean.Section;
import ma.learn.quiz.dao.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private ReponseService reponseService;
    @Autowired
    private QuestionService questionService;

    public void update(Quiz quiz) {
        quizDao.save(quiz);
    }

    public Quiz findByRef(String ref) {
        return quizDao.findByRef(ref);
    }

    public Quiz findBySectionId(Long id) {
        return quizDao.findBySectionId(id);
    }

    @Transactional
    public void deleteById(Quiz quiz) {
        for (Question q : quiz.getQuestions()
        ) {
            this.reponseService.deleteAllByQuestionId(q.getId());
        }
        this.questionService.deleteAllByQuizId(quiz.getId());
        quizDao.deleteById(quiz.getId());
    }

    @Transactional
    public int deleteByRef(String ref) {
        int a = reponseService.deleteByQuestionQuizRef(ref);
        int b = questionService.deleteByQuizRef(ref);
        int c = quizDao.deleteByRef(ref);
        return a + b + c;
    }

    public Quiz findQuizById(Long id) {
        return quizDao.findQuizById(id);
    }

    public List<Quiz> findAll() {
        return quizDao.findAll();
    }

    public int save(Quiz quiz) {
        if (findByRef(quiz.getRef()) != null) {
            return -1;
        } else {
            quizDao.save(quiz);
            return 1;
        }
    }

    public int saveAll(Quiz quiz) {
        Section section = sectionService.findSectionById(quiz.getSection().getId());
        quiz.setLib(section.getCategorieSection().getLibelle());
        quiz.setSection(section);
        quizDao.save(quiz);
        questionService.saveAll(quiz, quiz.getQuestions());
        return 1;
    }

    public Quiz updateAll(Quiz quiz) {
        List<Question> questionList1 = quiz.getQuestions();

        System.out.println("QST SIZE" + questionList1.size());
        for (Question q : questionList1
        ) {
            System.out.println(q.getId());
            System.out.println(q.getReponses().size());
            System.out.println(q.getTypeDeQuestion());
            System.out.println(q.getLibelle());
            System.out.println(q.getNumero());
        }
        List<Question> questionList = questionService.saveAll(quiz, questionList1);
        Quiz quiz1 = quizDao.save(quiz);
        quiz1.setQuestions(questionList);
        return quiz1;
    }


}
