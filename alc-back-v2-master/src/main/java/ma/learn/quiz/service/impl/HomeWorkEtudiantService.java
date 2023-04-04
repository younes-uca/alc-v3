package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.bean.HomeWork;
import ma.learn.quiz.bean.HomeWorkEtudiant;
import ma.learn.quiz.dao.HomeWorkDao;
import ma.learn.quiz.dao.HomeWorkEtudiantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Service
public class HomeWorkEtudiantService {

    public Optional<HomeWorkEtudiant> findById(Long id) {
        return homeWorkEtudiantDao.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        homeWorkEtudiantDao.deleteById(id);
    }

    public List<HomeWorkEtudiant> findByEtudiantId(Long id) {
        return homeWorkEtudiantDao.findByEtudiantId(id);
    }

    public List<HomeWorkEtudiant> findByHomeWorkId(Long id) {
        return homeWorkEtudiantDao.findByHomeWorkId(id);
    }

    @Transactional
    public int deleteByEtudiantId(Long id) {
        return homeWorkEtudiantDao.deleteByEtudiantId(id);
    }

    @Transactional
    public int deleteByHomeWorkId(Long id) {
        return homeWorkEtudiantDao.deleteByHomeWorkId(id);
    }

    public List<HomeWorkEtudiant> findAll() {
        return homeWorkEtudiantDao.findAll();
    }

    public HomeWorkEtudiant save(HomeWorkEtudiant homeWorkEtudiant) {
        HomeWork homeWork = homeWorkDao.findHomeWorkById(homeWorkEtudiant.getHomeWork().getId());
        Etudiant etd = this.etudiantService.findEtudiantById(homeWorkEtudiant.getEtudiant().getId());
        if (homeWorkEtudiant.getEtudiant() == null) {
            return null;
        } else if (etd == null) {
            return null;
        } else {
            homeWorkEtudiant.setHomeWork(homeWork);
            homeWorkEtudiant.setEtudiant(etd);
            return homeWorkEtudiantDao.save(homeWorkEtudiant);
        }
    }

    public int update(HomeWorkEtudiant homeWorkEtudiant) {
        Optional<HomeWorkEtudiant> aploadedHomeWorkEtudiant = this.homeWorkEtudiantDao.findById(homeWorkEtudiant.getId());
        if (aploadedHomeWorkEtudiant.isPresent()) {
            aploadedHomeWorkEtudiant.get().setNote(homeWorkEtudiant.getNote());
            aploadedHomeWorkEtudiant.get().setResultat(homeWorkEtudiant.getResultat());
            this.homeWorkEtudiantDao.save(aploadedHomeWorkEtudiant.get());
        }
        reponseEtudiantHomeWorkService.update(homeWorkEtudiant, homeWorkEtudiant.getReponseEtudiantHomeWork());
        return 1;
    }

    @Autowired
    private HomeWorkEtudiantDao homeWorkEtudiantDao;
    @Autowired
    private HomeWorkDao homeWorkDao;
    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private ReponseEtudiantHomeWorkService reponseEtudiantHomeWorkService;
    @Autowired
    private EntityManager entityManager;


    public List<HomeWorkEtudiant> findByCritere(Long idEtudiant, Long idHomeWork) {
        String query = "SELECT h FROM HomeWorkEtudiant h WHERE h.etudiant.id= '" + idEtudiant + "' and h.homeWork.id='" + idHomeWork + "'";
        return entityManager.createQuery(query).getResultList();
    }

    public List<HomeWorkEtudiant> findByProfId(Long id) {
        String query = "SELECT h FROM HomeWorkEtudiant h WHERE h.etudiant.prof.id='" + id + "'";
        return entityManager.createQuery(query).getResultList();
    }

    public List<HomeWorkEtudiant> findByVo(HomeWorkEtudiant homeWorkEtudiant) {
        String query = "SELECT h FROM HomeWorkEtudiant h WHERE 1=1";
        if (homeWorkEtudiant.getEtudiant().getNom() != null) {
            query += "AND h.etudiant.nom = 'homeWorkEtudiant.getEtudiant().getNom()'";
        }
        if (homeWorkEtudiant.getHomeWork().getLibelle() != null) {
            query += "AND h.homework.libelle = 'homeWorkEtudiant.getHomeWork().getLibelle()'";
        }
        if (homeWorkEtudiant.getNote() != null) {
            query += "AND h.note = 'homeWorkEtudiant.getNote()'";
        }
        return entityManager.createQuery(query).getResultList();
    }

}
