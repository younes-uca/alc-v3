package ma.learn.quiz.dao;

import ma.learn.quiz.bean.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillDao extends JpaRepository<Skill, Long> {
    Skill findByCode(String code);
     int deleteByLibelle(String libelle);
}
