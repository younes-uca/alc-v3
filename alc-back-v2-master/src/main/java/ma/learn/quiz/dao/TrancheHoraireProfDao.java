package ma.learn.quiz.dao;

import ma.learn.quiz.bean.TrancheHoraireProf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrancheHoraireProfDao extends JpaRepository<TrancheHoraireProf, Long> {

    int deleteTrancheHoraireProfById(Long id);
    List<TrancheHoraireProf> findByProfId(Long id);
    TrancheHoraireProf findTrancheHoraireProfById(Long id);

}
