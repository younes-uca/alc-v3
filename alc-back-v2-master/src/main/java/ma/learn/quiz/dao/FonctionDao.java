package ma.learn.quiz.dao;

import ma.learn.quiz.bean.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FonctionDao extends JpaRepository<Fonction, Long> {
    Fonction findByCode(String code);

    int deleteByLibelle(String libelle);
}
