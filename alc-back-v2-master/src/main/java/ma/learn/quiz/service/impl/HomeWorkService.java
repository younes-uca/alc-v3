package ma.learn.quiz.service.impl;


import ma.learn.quiz.bean.Cours;
import ma.learn.quiz.bean.HomeWork;
import ma.learn.quiz.bean.HomeWorkQuestion;
import ma.learn.quiz.bean.TypeHomeWork;
import ma.learn.quiz.dao.HomeWorkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class HomeWorkService {

    @Autowired
    SectionService sectionService;
    @Autowired
    CoursService coursService;
    @Autowired
    HomeWorkQuestionService homeWorkQuestionService;
    @Autowired
    QuestionService questionService;

    public Optional<HomeWork> findById(Long id) {
        return homeWorkDao.findById(id);
    }

    public HomeWork findHomeWorkById(Long id) {
        return homeWorkDao.findHomeWorkById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        homeWorkDao.deleteById(id);
    }

    @Cacheable(value = "homeworkListByCourseId")
    public List<HomeWork> findByCoursId(Long id) {
        return homeWorkDao.findByCoursId(id);
    }

    public List<HomeWork> findAll() {
        return homeWorkDao.findAll();
    }

    public HomeWork save(HomeWork homeWork) {
        System.out.println(homeWork.getLibelle());
        List<HomeWorkQuestion> questions = homeWork.getQuestions();
        if (homeWork.getId() != null) {
            Optional<HomeWork> loadedHomeWork = this.findById(homeWork.getId());
            if (loadedHomeWork.isPresent()) {
                HomeWork homeWork1 = loadedHomeWork.get();
                homeWork1.setUrlImage(homeWork.getUrlImage());
                homeWork1.setLibelle(homeWork.getLibelle());
                homeWork1.setUrlVideo(homeWork.getUrlVideo());
                homeWork = homeWorkDao.save(homeWork1);
            }
        } else {
            Cours cours = coursService.findCoursById(homeWork.getCours().getId());
            Optional<TypeHomeWork> typeHomeWork = this.typeHomeWorkService.findById(homeWork.getTypeHomeWork().getId());
            if (typeHomeWork.isPresent()) {
                homeWork.setTypeHomeWork(typeHomeWork.get());
            }
            homeWork.setCours(cours);
            homeWork = homeWorkDao.save(homeWork);
        }


        homeWorkQuestionService.saveHomeWorkQuestion(homeWork, questions);
        return homeWork;
    }

    @Autowired
    private HomeWorkDao homeWorkDao;
    @Autowired
    private TypeHomeWorkService typeHomeWorkService;

}
