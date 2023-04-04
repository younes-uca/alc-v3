package ma.learn.quiz.dao;


import ma.learn.quiz.bean.HoweWorkQSTReponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeWorkQSTReponseDao extends JpaRepository<HoweWorkQSTReponse,Long> {
    List<HoweWorkQSTReponse> findByHomeWorkQuestionId(Long id);
    List<HoweWorkQSTReponse> findByEtatReponse(String etatreponse);
    HoweWorkQSTReponse findHoweWorkQSTReponseById(Long id);
}
