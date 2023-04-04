package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.TypeReclamationProf;
import ma.learn.quiz.dao.TypeReclamationProfDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class TypeReclamationProfService {

    @Autowired
    private TypeReclamationProfDao typeReclamationProfDao;

    public TypeReclamationProf findTypeReclamationProfByCode(String code) {
        return typeReclamationProfDao.findTypeReclamationProfByCode(code);
    }

    @Transactional
    public int deleteTypeReclamationProfById(Long id) {
        return typeReclamationProfDao.deleteTypeReclamationProfById(id);
    }

    public int saveTypeReclamationProf(TypeReclamationProf typeReclamationProf) {
        if (findTypeReclamationProfByCode(typeReclamationProf.getCode()) != null) {
            return -1;
        } else {
            TypeReclamationProf typeReclamationProf1 = new TypeReclamationProf();
            typeReclamationProf1.setCode(typeReclamationProf.getCode());
            typeReclamationProf1.setLibelle(typeReclamationProf.getCode());
            typeReclamationProfDao.save(typeReclamationProf1);
            return 1;
        }
    }

    public List<TypeReclamationProf> findAll() {
        return typeReclamationProfDao.findAll();
    }
}
