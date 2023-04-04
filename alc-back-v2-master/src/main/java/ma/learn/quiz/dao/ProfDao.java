package ma.learn.quiz.dao;

import ma.learn.quiz.bean.Prof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfDao extends JpaRepository<Prof, Long> {
    Prof findByNumero(String ref);

    Prof findByNom(String nom);

    Prof findByUsername(String username);

    Prof findProfById(Long id);

    int deleteByNumero(String ref);

    Prof findByRef(String ref);

    int deleteByRef(String ref);

    int deleteProfById(Long id);
}