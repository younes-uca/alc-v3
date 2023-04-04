package ma.learn.quiz.dao;

import ma.learn.quiz.bean.FaqProf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqProfDao extends JpaRepository<FaqProf, Long> {
    List<FaqProf> findByFaqTypeId(Long id);

    int deleteByFaqTypeId(Long id);

    List<FaqProf> findByProfId(Long id);

    int deleteByProfId(Long id);

    FaqProf findByLibelle(String libelle);
}
