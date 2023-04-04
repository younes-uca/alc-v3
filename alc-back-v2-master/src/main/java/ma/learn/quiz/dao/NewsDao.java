package ma.learn.quiz.dao;

import ma.learn.quiz.bean.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsDao extends JpaRepository<News, Long> {

    News findByRef(String ref);

    int deleteByRef(String ref);

}
