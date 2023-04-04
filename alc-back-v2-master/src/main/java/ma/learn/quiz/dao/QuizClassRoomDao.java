package ma.learn.quiz.dao;

import ma.learn.quiz.bean.QuizClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuizClassRoomDao extends JpaRepository<QuizClassRoom, Long> {

    List<QuizClassRoom> findByClassRoomId(Long id);

    int deleteByClassRoomId(Long id);
}
