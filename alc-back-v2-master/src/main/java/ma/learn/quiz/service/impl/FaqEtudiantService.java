package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.FaqEtudiant;
import ma.learn.quiz.bean.FaqProf;
import ma.learn.quiz.dao.FaqEtudiantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class FaqEtudiantService {

    @Autowired
    private FaqEtudiantDao faqEtudiantDao;


    public List<FaqEtudiant> findAll() {
        return faqEtudiantDao.findAll();
    }


    public Optional<FaqEtudiant> findById(Long id) {
        return faqEtudiantDao.findById(id);
    }


    @Transactional
    public void deleteById(Long id) {
        faqEtudiantDao.deleteById(id);
    }


    public List<FaqEtudiant> findByFaqTypeId(Long id) {
        return faqEtudiantDao.findByFaqTypeId(id);
    }


    @Transactional
    public int deleteByFaqTypeId(Long id) {
        return faqEtudiantDao.deleteByFaqTypeId(id);
    }


    public List<FaqEtudiant> findByEtudiantId(Long id) {
        return faqEtudiantDao.findByEtudiantId(id);
    }


    @Transactional
    public int deleteByEtudiantId(Long id) {
        return faqEtudiantDao.deleteByEtudiantId(id);
    }


    public FaqEtudiant findByLibelle(String libelle) {
        return faqEtudiantDao.findByLibelle(libelle);
    }


    public int save(FaqEtudiant faqEtudiant) {
        faqEtudiantDao.save(faqEtudiant);
        return 1;
    }

    public List<FaqProf> findByCritere(Long idEtudiant, Long idType) {
        String query = "SELECT f FROM FaqEtudiant f WHERE f.etudiant.id= '" + idEtudiant + "' and f.faqType.id='" + idType + "'";
        return entityManager.createQuery(query).getResultList();
    }

    public void update(FaqEtudiant faqEtudiant) {
        faqEtudiant.setAdmin(faqEtudiant.getAdmin());
        faqEtudiant.setDescription(faqEtudiant.getDescription());
        faqEtudiantDao.save(faqEtudiant);

    }

    @Autowired
    private EntityManager entityManager;


}
