package ma.learn.quiz.dao;

import ma.learn.quiz.bean.NiveauEtude;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NiveauEtudeDao extends JpaRepository<NiveauEtude, Long> {
    NiveauEtude findByCode(String code);

    int deleteByLibelle(String libelle);

}
