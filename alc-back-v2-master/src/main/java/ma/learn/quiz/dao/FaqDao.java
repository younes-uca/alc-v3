package ma.learn.quiz.dao;

import ma.learn.quiz.bean.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqDao extends JpaRepository<Faq, Long> {
    List<Faq> findByFaqTypeId(Long id);

    int deleteByFaqTypeId(Long id);
}
