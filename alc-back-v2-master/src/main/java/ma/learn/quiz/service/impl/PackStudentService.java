package ma.learn.quiz.service.impl;


import ma.learn.quiz.bean.PackStudent;
import ma.learn.quiz.dao.PackStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PackStudentService {


    @Autowired
    private PackStudentDao packStudentDao;
    @Autowired
    private EntityManager entityManager;


    public List<PackStudent> findAll() {
        return packStudentDao.findAll();
    }

    public PackStudent findPackStudentByCode(String code) {
        return packStudentDao.findPackStudentByCode(code);
    }

    public List<PackStudent> findByLevelId(Long id) {
        return packStudentDao.findByLevelId(id);
    }

    public PackStudent findPackStudentByPrix(Double prix) {
        return packStudentDao.findPackStudentByPricePrice(prix);
    }

    public List<PackStudent> findPackStudentByForGroupe(boolean forgroupe) {
        return packStudentDao.findPackStudentByForGroupe(forgroupe);
    }

    public PackStudent findPackStudentByLibelle(String libelle) {
        return packStudentDao.findPackStudentByLibelle(libelle);
    }

    public List<PackStudent> findByTotalStudents(int totalStudents) {
        return packStudentDao.findByTotalStudents(totalStudents);
    }

    public PackStudent save(PackStudent packStudent) throws Exception {
       return packStudentDao.save(packStudent);
    }

    public PackStudent update(PackStudent packStudent) {
            return packStudentDao.save(packStudent);
    }

    public List<PackStudent> findbyCriteria(PackStudent packStudent) {
        String query = "SELECT p FROM PackStudent p WHERE 1=1 AND p.forGroupe = " + packStudent.isForGroupe();
        if (packStudent.getNombreCours() > 0) {
            query += " AND p.nombreCours = " + packStudent.getNombreCours();
        }
        if (packStudent.getPrice() != null) {
            query += " AND p.prix = " + packStudent.getPrice().getPrice();
        }
        if (packStudent.getCode() != null && !(packStudent.getCode().isEmpty())) {
            query += " AND p.code LIKE '" + packStudent.getCode() + "'";
        }
        if (packStudent.getTotalStudents() > 0) {
            query += " AND p.totalStudents = " + packStudent.getTotalStudents();
        }
        if (packStudent.getLibelle() != null && !(packStudent.getLibelle().isEmpty())) {
            query += " AND p.libelle LIKE '" + packStudent.getLibelle() + "'";
        }
        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
         packStudentDao.deleteById(id);
    }



    @Transactional
    public int deleteByForGroupe(boolean forGroupe) {
        return packStudentDao.deleteByForGroupe(forGroupe);
    }

    public PackStudent findById(Long pack) {
        Optional<PackStudent> pack1 = packStudentDao.findById(pack);
        return pack1.orElse(null);
    }
}
