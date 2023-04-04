package ma.learn.quiz.dao;

import ma.learn.quiz.bean.GroupeEtude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeEtudeDao extends JpaRepository<GroupeEtude, Long> {
    GroupeEtude findGroupeEtudeById(Long id);

    GroupeEtude findByLibelle(String libelle);

    int deleteByLibelle(String libelle);

    int deleteGroupeEtudeById(Long id);

    GroupeEtude findByNombreEtudiant(Long numberOfStudent);
}
