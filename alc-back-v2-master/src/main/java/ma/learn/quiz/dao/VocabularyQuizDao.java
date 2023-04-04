package ma.learn.quiz.dao;

import ma.learn.quiz.bean.VocabularyQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabularyQuizDao extends JpaRepository<VocabularyQuiz, Long>{
 VocabularyQuiz findByRef(String ref);
	int deleteByRef(String Ref);
	VocabularyQuiz findBySectionId(Long id);
}
