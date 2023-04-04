package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.TypeReclamationEtudiant;
import ma.learn.quiz.dao.TypeReclamationEtudiantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class TypeReclamationEtudiantService {
    @Autowired
    private TypeReclamationEtudiantDao typeReclamationEtudiantDao;

    public TypeReclamationEtudiant findTypeReclamationEtudiantByCode(String code) {
        return typeReclamationEtudiantDao.findTypeReclamationEtudiantByCode(code);
    }

    @Transactional
    public int deleteTypeReclamationEtudiantById(Long id) {
        return typeReclamationEtudiantDao.deleteTypeReclamationEtudiantById(id);
    }

    public int saveTypeReclamationEtudiant(TypeReclamationEtudiant typeReclamationEtudiant) {
        if (findTypeReclamationEtudiantByCode(typeReclamationEtudiant.getCode()) != null) {
            return -1;
        } else {
            TypeReclamationEtudiant typeReclamationEtudiant1 = new TypeReclamationEtudiant();
            typeReclamationEtudiant1.setCode(typeReclamationEtudiant.getCode());
            typeReclamationEtudiant1.setLibelle(typeReclamationEtudiant.getCode());
            typeReclamationEtudiantDao.save(typeReclamationEtudiant1);
            return 1;
        }
    }

    public List<TypeReclamationEtudiant> findAll() {
        return typeReclamationEtudiantDao.findAll();
    }
}
