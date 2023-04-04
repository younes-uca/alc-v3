package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.InteretEtudiant;
import ma.learn.quiz.dao.InteretEtudiantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InteretEtudiantService {
    @Autowired
    private InteretEtudiantDao interetEtudiantDao;

    public List<InteretEtudiant> findAll() {
        return interetEtudiantDao.findAll();
    }
    public int save (InteretEtudiant interetEtudiant){
        interetEtudiantDao.save(interetEtudiant);
        return 1;
    }


    public int update(InteretEtudiant interetEtudiant){
        interetEtudiantDao.save(interetEtudiant);
        return 1;
    }
    @Transactional
    public int deleteByLibelle(String libelle) {
        return interetEtudiantDao.deleteByLibelle(libelle);
    }
}
