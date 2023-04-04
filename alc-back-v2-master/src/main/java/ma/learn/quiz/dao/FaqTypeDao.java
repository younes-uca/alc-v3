package ma.learn.quiz.dao;

import ma.learn.quiz.bean.FaqType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqTypeDao extends JpaRepository<FaqType, Long> {
    List<FaqType> findByDestinataire(String destinataire);

}
