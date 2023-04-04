package ma.learn.quiz.dao;

import ma.learn.quiz.bean.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRoomDao extends JpaRepository<ClassRoom, Long> {
    List<ClassRoom> findByResponsableId(Long id);

    int deleteByResponsableId(Long id);
}
