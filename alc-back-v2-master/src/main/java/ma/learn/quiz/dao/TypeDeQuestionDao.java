package ma.learn.quiz.dao;


import ma.learn.quiz.bean.TypeDeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDeQuestionDao extends JpaRepository<TypeDeQuestion, Long> {
    TypeDeQuestion findByRef(String ref);
    int deleteByRef(String ref);
    TypeDeQuestion findByLibIgnoreCase(String lib);
}