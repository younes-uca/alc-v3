package ma.learn.quiz.dao;

import ma.learn.quiz.bean.TypeReclamationEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeReclamationEtudiantDao extends JpaRepository<TypeReclamationEtudiant, Long> {
    TypeReclamationEtudiant findTypeReclamationEtudiantByCode(String code);
    int deleteTypeReclamationEtudiantById(Long id);

}
