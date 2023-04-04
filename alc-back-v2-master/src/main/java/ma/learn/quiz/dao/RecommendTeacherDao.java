package ma.learn.quiz.dao;

import ma.learn.quiz.bean.RecommendTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendTeacherDao extends JpaRepository<RecommendTeacher, Long> {

    RecommendTeacher findRecommendTeacherById(Long id);

    List<RecommendTeacher> findRecommendTeacherByProfId(Long id);

}
