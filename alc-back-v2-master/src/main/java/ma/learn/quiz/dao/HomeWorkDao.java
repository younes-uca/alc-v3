package ma.learn.quiz.dao;


import ma.learn.quiz.bean.HomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HomeWorkDao extends JpaRepository<HomeWork, Long> {
    List<HomeWork> findByCoursId(Long id);

    HomeWork findByLibelleAndCoursId(String libelle, Long id);

    HomeWork findHomeWorkById(Long id);
}