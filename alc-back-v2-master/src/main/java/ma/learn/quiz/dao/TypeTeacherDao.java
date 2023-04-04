package ma.learn.quiz.dao;

import ma.learn.quiz.bean.TypeTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeTeacherDao extends JpaRepository<TypeTeacher, Long> {
    int deleteByLibelle(String libelle);

}
