package ma.learn.quiz.dao;

import ma.learn.quiz.bean.QuizEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuizEtudiantDao extends JpaRepository<QuizEtudiant, Long> {
    QuizEtudiant findByRef(String ref);

    QuizEtudiant findQuizEtudiantById(Long id);

    List<QuizEtudiant> findByEtudiantId(Long id);

    QuizEtudiant findByEtudiantIdAndQuizId(Long idEtudiant, Long idQuiz);

    QuizEtudiant findByEtudiantIdAndQuizRef(Long idEtudiant, String ref);

    List<QuizEtudiant> findByResultat(String resultat);

    List<QuizEtudiant> findByQuizRef(String ref);

    List<QuizEtudiant> findByQuizId(Long id);

    int deleteByQuizRef(String ref);
}