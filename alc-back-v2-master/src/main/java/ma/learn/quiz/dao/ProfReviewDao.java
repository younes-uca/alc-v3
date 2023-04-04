package ma.learn.quiz.dao;

import ma.learn.quiz.bean.ProfReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfReviewDao extends JpaRepository<ProfReview, Long> {
    ProfReview findByReview(int review);

    int deleteByReview(int review);

    ProfReview findByEtudiantIdAndCoursIdAndProfId(long id, long ids, long idd);
}
