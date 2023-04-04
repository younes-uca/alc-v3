package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Section;
import ma.learn.quiz.bean.VocabularyQuiz;
import ma.learn.quiz.dao.VocabularyQuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VocabularyQuizService {

    public void update(VocabularyQuiz vocabularyQuiz) {
        vocabularyQuizDao.save(vocabularyQuiz);
    }

    public VocabularyQuiz findByRef(String ref) {
        return vocabularyQuizDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String Ref) {
        return vocabularyQuizDao.deleteByRef(Ref);
    }

    public VocabularyQuiz findBySectionId(Long id) {
        return vocabularyQuizDao.findBySectionId(id);
    }

    public int save(VocabularyQuiz vocabularyQuiz) {
        Section section = sectionService.findSectionById(vocabularyQuiz.getSection().getId());
        if (findByRef(vocabularyQuiz.getRef()) != null) {
            return -1;
        } else {
            vocabularyQuiz.setLibelle(section.getCategorieSection().getLibelle());
            vocabularyQuiz.setSection(section);
            vocabularyQuizDao.save(vocabularyQuiz);
            vocabularyService.save(vocabularyQuiz, vocabularyQuiz.getVocabularies());
            return 1;
        }
    }

    public List<VocabularyQuiz> findAll() {
        return vocabularyQuizDao.findAll();
    }

    @Autowired
    private VocabularyQuizDao vocabularyQuizDao;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private VocabularyService vocabularyService;
}
