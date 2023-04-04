package ma.learn.quiz.dao;


import ma.learn.quiz.bean.ScheduleProf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleProfDao extends JpaRepository<ScheduleProf, Long> {
    ScheduleProf findByRef(String ref);

    List<ScheduleProf> findByProfIdOrderByCoursNumeroOrder(Long id);

    List<ScheduleProf> findByGroupeEtudiantIdOrderByCoursNumeroOrder(Long id);


    int deleteByRef(String ref);
}

