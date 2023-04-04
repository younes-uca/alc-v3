package ma.learn.quiz.dao;


import ma.learn.quiz.bean.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface QuizDao extends JpaRepository<Quiz, Long> {
    Quiz findByRef(String ref);

    Quiz findQuizById(Long id);

    @Transactional
    int deleteByRef(String ref);

    Quiz findBySectionId(Long id);
}