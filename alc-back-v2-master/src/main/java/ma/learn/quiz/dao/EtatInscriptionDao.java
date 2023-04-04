package ma.learn.quiz.dao;

import ma.learn.quiz.bean.EtatInscription;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EtatInscriptionDao extends JpaRepository<EtatInscription, Long> {

    int deleteByRef(String ref);

    EtatInscription findByRef(String ref);

    EtatInscription findEtatInscriptionById(Long id);
}
