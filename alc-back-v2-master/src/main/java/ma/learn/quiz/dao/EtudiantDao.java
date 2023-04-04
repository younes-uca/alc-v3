package ma.learn.quiz.dao;

import ma.learn.quiz.bean.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EtudiantDao extends JpaRepository<Etudiant, Long> {
    Etudiant findByRef(String ref);

    Etudiant findByNom(String nom);

    Etudiant findByUsername(String username);

    int deleteByRef(String ref);

    int deleteByNom(String nom);

    List<Etudiant> findByParcoursCode(String code);

    Etudiant findEtudiantById(Long id);

    int deleteByParcoursId(Long id);

    int deleteEtudiantById(Long id);

    int deleteByParcoursCode(String code);


    List<Etudiant> findByParcoursLibelle(String libelle);

    List<Etudiant> findEtudiantByGroupeEtudiantDetailsGroupeEtudiantParcours(String libelle);

}
