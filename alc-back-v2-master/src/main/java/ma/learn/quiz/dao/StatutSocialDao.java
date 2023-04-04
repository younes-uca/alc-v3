package ma.learn.quiz.dao;

import ma.learn.quiz.bean.StatutSocial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutSocialDao extends JpaRepository<StatutSocial, Long> {
    StatutSocial findByCode(String code);
    int deleteByLibelle(String libelle);

}
