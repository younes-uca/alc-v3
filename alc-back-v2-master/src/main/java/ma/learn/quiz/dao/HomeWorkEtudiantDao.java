package ma.learn.quiz.dao;

import ma.learn.quiz.bean.HomeWorkEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HomeWorkEtudiantDao extends JpaRepository<HomeWorkEtudiant, Long> {
    List<HomeWorkEtudiant> findByEtudiantId(Long id);

    List<HomeWorkEtudiant> findByHomeWorkId(Long id);

    int deleteByEtudiantId(Long id);

    int deleteByHomeWorkId(Long id);
}