package ma.learn.quiz.dao;

import ma.learn.quiz.bean.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementDao extends JpaRepository<Paiement, Long> {

    List<Paiement> findPaiementByProfId(Long id);


}