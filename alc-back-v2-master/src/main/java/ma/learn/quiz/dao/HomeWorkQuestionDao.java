package ma.learn.quiz.dao;

import ma.learn.quiz.bean.HomeWork;
import ma.learn.quiz.bean.HomeWorkQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HomeWorkQuestionDao extends JpaRepository<HomeWorkQuestion, Long> {

    List<HomeWorkQuestion> findHomeWorkQuestionByHomeWorkIdOrderByNumero(Long id);

    HomeWorkQuestion findHomeWorkQuestionByLibelleAndHomeWorkId(String libelle, Long id);

    HomeWorkQuestion findHomeWorkQuestionById(Long id);

    int deleteByRef(String ref);

    @Transactional
    int deleteHomeWorkQuestionById(Long id);

    int deleteByHomeWork(HomeWork homeWork);
}
