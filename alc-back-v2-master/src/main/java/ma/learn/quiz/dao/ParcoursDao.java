package ma.learn.quiz.dao;


import ma.learn.quiz.bean.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcoursDao extends JpaRepository<Parcours, Long> {

    Parcours findParcoursById(Long id);

    List<Parcours> findByCode(String code);

    int deleteParcoursById(Long id);

    Parcours findParcoursByLibelle(String libelle);

    List<Parcours> findByCentreRef(String ref);

    int deleteByCentreRef(String Ref);


    Parcours findByLibelle(String libelle);

}
