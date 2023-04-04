package ma.learn.quiz.dao;

import ma.learn.quiz.bean.TypeReclamationProf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeReclamationProfDao extends JpaRepository<TypeReclamationProf, Long> {
    TypeReclamationProf findTypeReclamationProfByCode(String code);
    int deleteTypeReclamationProfById(Long id);
}
