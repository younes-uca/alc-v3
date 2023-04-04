package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.HomeWorkEtudiant;
import ma.learn.quiz.bean.ReponseEtudiantHomeWork;
import ma.learn.quiz.dao.HomeWorkEtudiantDao;
import ma.learn.quiz.dao.ReponseEtudiantHomeWorkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Service
public class ReponseEtudiantHomeWorkService {

    public Optional<ReponseEtudiantHomeWork> findById(Long id) {
        return reponseEtudiantHomeWorkDao.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        reponseEtudiantHomeWorkDao.deleteById(id);
    }


    public List<ReponseEtudiantHomeWork> findByHomeWorkEtudiantId(Long id) {
        return reponseEtudiantHomeWorkDao.findReponseEtudiantHomeWorkByHomeWorkEtudiantId(id);
    }

    public List<ReponseEtudiantHomeWork> findByHomeWorkEtudiantEtudiantId(Long id) {
        return reponseEtudiantHomeWorkDao.findByHomeWorkEtudiantEtudiantId(id);
    }

    public List<ReponseEtudiantHomeWork> findByHomeWorkEtudiantHomeWorkId(Long id) {
        return reponseEtudiantHomeWorkDao.findByHomeWorkEtudiantHomeWorkId(id);
    }


    @Transactional
    public int deleteByHomeWorkEtudiantId(Long id) {
        return reponseEtudiantHomeWorkDao.deleteByHomeWorkEtudiantId(id);
    }

    @Transactional
    public int deleteByHomeWorkEtudiantEtudiantId(Long id) {
        return reponseEtudiantHomeWorkDao.deleteByHomeWorkEtudiantEtudiantId(id);
    }

    @Transactional
    public int deleteByHomeWorkEtudiantHomeWorkId(Long id) {
        return reponseEtudiantHomeWorkDao.deleteByHomeWorkEtudiantHomeWorkId(id);
    }

    public List<ReponseEtudiantHomeWork> findAll() {
        return reponseEtudiantHomeWorkDao.findAll();
    }

    public ReponseEtudiantHomeWork save(ReponseEtudiantHomeWork reponseEtudiantHomeWork) {
        return reponseEtudiantHomeWorkDao.save(reponseEtudiantHomeWork);
    }


    public int update(HomeWorkEtudiant homeWorkEtudiant, List<ReponseEtudiantHomeWork> reponseEtudiantHomeWorkList) {
        for (ReponseEtudiantHomeWork reponseEtudiantHomeWork1 : reponseEtudiantHomeWorkList) {
            Optional<ReponseEtudiantHomeWork> reponseEtudiantHomeWork = reponseEtudiantHomeWorkDao.findById(reponseEtudiantHomeWork1.getId());
            if (reponseEtudiantHomeWork.isPresent()) {
                reponseEtudiantHomeWork.get().setProfNote(reponseEtudiantHomeWork1.getProfNote());
                reponseEtudiantHomeWork.get().setReponse(reponseEtudiantHomeWork1.getReponse());
                reponseEtudiantHomeWork.get().setAnswer(reponseEtudiantHomeWork1.getAnswer());
                reponseEtudiantHomeWork.get().setNote(reponseEtudiantHomeWork1.getNote());
                reponseEtudiantHomeWorkDao.save(reponseEtudiantHomeWork.get());
            } else {
                reponseEtudiantHomeWork1.setHomeWorkEtudiant(homeWorkEtudiant);
                reponseEtudiantHomeWorkDao.save(reponseEtudiantHomeWork1);
            }
        }
        return 1;
    }

    @Autowired
    private ReponseEtudiantHomeWorkDao reponseEtudiantHomeWorkDao;
    @Autowired
    private HomeWorkEtudiantDao homeWorkEtudiantDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private HomeWorkQSTReponseService homeWorkQSTReponseService;

    public List<ReponseEtudiantHomeWork> findByCriteria(Long idHomeWorkEtudiant, Long numeroQuestion) {
        String query = "SELECT r FROM ReponseEtudiantHomeWork r WHERE r.homeWorkEtudiant.id= '" + idHomeWorkEtudiant + "' and r.reponse.homeWorkQuestion.numero='" + numeroQuestion + "'";
        return entityManager.createQuery(query).getResultList();
    }


    public List<ReponseEtudiantHomeWork> findReponseEtudiantHomeWorkByQuestionId(Long id) {
        return reponseEtudiantHomeWorkDao.findReponseEtudiantHomeWorkByQuestionId(id);
    }
}
