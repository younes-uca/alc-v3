package ma.learn.quiz.dao;

import ma.learn.quiz.bean.ReponseEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReponseEtudiantDao extends JpaRepository<ReponseEtudiant, Long> {
    ReponseEtudiant findByRef(String ref);

    int deleteByRef(String Ref);

    List<ReponseEtudiant> findByReponseQuestionRef(String ref);

    List<ReponseEtudiant> findByQuizEtudiantEtudiantRef(String ref);

    List<ReponseEtudiant> findByQuizEtudiantEtudiantId(Long id);

    List<ReponseEtudiant> findByQuizEtudiantRef(String ref);

    List<ReponseEtudiant> findByQuizEtudiantIdOrderByQuestionNumero(Long id);


    int deleteByReponseQuestionRef(String ref);

    int deleteByQuizEtudiantEtudiantRef(String ref);

    int deleteByQuizEtudiantId(Long id);
}