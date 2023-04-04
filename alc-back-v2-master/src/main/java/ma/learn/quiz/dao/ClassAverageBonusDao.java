package ma.learn.quiz.dao;

import ma.learn.quiz.bean.ClassAverageBonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassAverageBonusDao extends JpaRepository<ClassAverageBonus, Long> {
    ClassAverageBonus findClassAverageBonusById(Long id);
    ClassAverageBonus findClassAverageBonusByNombreSession(int nombreSession);
 }
