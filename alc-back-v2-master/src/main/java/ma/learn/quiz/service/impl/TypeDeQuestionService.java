package ma.learn.quiz.service.impl;


import ma.learn.quiz.bean.TypeDeQuestion;
import ma.learn.quiz.dao.TypeDeQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TypeDeQuestionService {


    public void update(TypeDeQuestion typeDeQuestion) {
        typeDeQuestionDao.save(typeDeQuestion);
    }

    public TypeDeQuestion findByRef(String ref) {
        return typeDeQuestionDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        return typeDeQuestionDao.deleteByRef(ref);
    }

    public List<TypeDeQuestion> findAll() {
        return typeDeQuestionDao.findAll();
    }

    public int save(TypeDeQuestion typeDeQuestion) {
        if (findByRef(typeDeQuestion.getRef()) != null) {
            return -1;
        } else {
            typeDeQuestionDao.save(typeDeQuestion);
            return 1;
        }
    }

    @Autowired
    private TypeDeQuestionDao typeDeQuestionDao;
}
