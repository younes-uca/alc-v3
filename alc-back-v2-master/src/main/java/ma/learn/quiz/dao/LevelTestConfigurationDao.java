package ma.learn.quiz.dao;

import ma.learn.quiz.bean.LevelTestConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelTestConfigurationDao extends JpaRepository<LevelTestConfiguration, Long> {

}
