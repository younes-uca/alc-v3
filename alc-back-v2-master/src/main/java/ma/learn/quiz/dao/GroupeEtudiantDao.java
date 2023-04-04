package ma.learn.quiz.dao;

import ma.learn.quiz.bean.GroupeEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupeEtudiantDao extends JpaRepository<GroupeEtudiant, Long> {
    GroupeEtudiant findGroupeEtudiantById(Long id);

    GroupeEtudiant findByLibelle(String libelle);

    int deleteGroupeEtudiantById(Long id);

    List<GroupeEtudiant> findByParcoursIdAndNombrePlacevideGreaterThan(Long id, Long nombrePlacevide);

    List<GroupeEtudiant> findGroupeEtudiantByProfId(Long id);

    GroupeEtudiant findGroupeEtudiantByLibelleAndProfId(String libelle, Long idprof);


}
