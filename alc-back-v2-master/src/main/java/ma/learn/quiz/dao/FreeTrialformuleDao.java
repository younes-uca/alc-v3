package ma.learn.quiz.dao;


import ma.learn.quiz.bean.FreeTrialformule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeTrialformuleDao extends JpaRepository<FreeTrialformule, Long> {

    FreeTrialformule findByCode(String code);

    FreeTrialformule findByInscriptionId(Long id);

}
