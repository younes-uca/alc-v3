package ma.learn.quiz.dao;

import ma.learn.quiz.bean.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscriptionDao extends JpaRepository<Inscription, Long> {
    Inscription findByNumeroInscription(int numeroInscription);

    Inscription findInscriptionByEtudiantId(Long id);

    List<Inscription> findByDateRegistration(String dateRegistration);

    int deleteByNumeroInscription(int numeroInscription);

    int deleteByEtatInscriptionRef(String ref);

    int deleteByEtudiantRef(String ref);

    int deleteInscriptionById(Long id);

    int deleteInscriptionByEtudiantId(Long id);

    Inscription findInscriptionById(Long id);

    Inscription findInscriptionByEtudiantUsername(String username);

    List<Inscription> findAllByOrderByIdDesc();

    List<Inscription> findAllByEtatInscriptionLibelleOrderByIdDesc(String libelle);
}
