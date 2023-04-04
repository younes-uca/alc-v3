package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.TypeTeacher;
import ma.learn.quiz.dao.TypeTeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TypeTeacherService {
    @Autowired
    private TypeTeacherDao typeTeacherDao;

    public List<TypeTeacher> findAll() {
        return typeTeacherDao.findAll();
    }

    public int save(TypeTeacher typeTeacher) {

        typeTeacherDao.save(typeTeacher);
        return 1;

    }

    public int update(TypeTeacher typeTeacher) {
        typeTeacherDao.save(typeTeacher);
        return 1;
    }

    @Transactional
    public int deleteByLibelle(String libelle) {
        return typeTeacherDao.deleteByLibelle(libelle);
    }
}
