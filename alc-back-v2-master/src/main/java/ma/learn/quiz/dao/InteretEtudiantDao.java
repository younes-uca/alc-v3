package ma.learn.quiz.dao;

import ma.learn.quiz.bean.InteretEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteretEtudiantDao extends JpaRepository<InteretEtudiant, Long> {
    InteretEtudiant findByCode(String code);

    int deleteByLibelle(String libelle);
}
