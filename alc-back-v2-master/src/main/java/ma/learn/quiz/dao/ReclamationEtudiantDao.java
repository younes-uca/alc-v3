package ma.learn.quiz.dao;

import ma.learn.quiz.bean.ReclamationEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReclamationEtudiantDao extends JpaRepository<ReclamationEtudiant, Long> {

    ReclamationEtudiant findReclamationEtudiantById(Long id);

    List<ReclamationEtudiant> findReclamationEtudiantByUserId(Long id);

    ReclamationEtudiant findReclamationEtudiantByIdAndUserId(Long id, Long idetudiant);
}
