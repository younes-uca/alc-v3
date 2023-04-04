package ma.learn.quiz.dao;

import ma.learn.quiz.bean.ReclamationProf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReclamationProfDao extends JpaRepository<ReclamationProf, Long> {

    ReclamationProf findReclamationProfById(Long id);

    List<ReclamationProf> findReclamationProfByProfId(Long id);

    ReclamationProf findReclamationProfByIdAndProfId(Long id, Long idprof);

}
