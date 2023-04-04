package ma.learn.quiz.dao;

import ma.learn.quiz.bean.CategorieProf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategorieProfDao extends JpaRepository<CategorieProf, Long> {
    CategorieProf findCategorieProfById(Long id);

    int deleteCategorieProfById(Long id);
}
