package ma.learn.quiz.dao;

import ma.learn.quiz.bean.FaqEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqEtudiantDao extends JpaRepository<FaqEtudiant, Long> {
    List<FaqEtudiant> findByFaqTypeId(Long id);

    int deleteByFaqTypeId(Long id);

    List<FaqEtudiant> findByEtudiantId(Long id);

    int deleteByEtudiantId(Long id);

    FaqEtudiant findByLibelle(String libelle);
}
