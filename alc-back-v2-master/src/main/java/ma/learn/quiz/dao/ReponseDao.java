package ma.learn.quiz.dao;


import ma.learn.quiz.bean.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReponseDao extends JpaRepository<Reponse, Long> {
    Reponse findByRef(String ref);

    Reponse findReponseById(Long id);

    List<Reponse> findByQuestionId(Long id);

    List<Reponse> findByQuestionNumero(Long numero);

    List<Reponse> findByEtatReponse(String etatReponse);


    int deleteByQuestionRef(String ref);

    int deleteByQuestionId(Long id);

    @Transactional
    int deleteByQuestionQuizRef(String ref);

    int deleteByRef(String ref);

    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteAllByQuestionId(Long id);
}
