package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.RecommendTeacher;
import ma.learn.quiz.dao.RecommendTeacherDao;
import ma.learn.quiz.service.vo.RecommendTeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendTeacherService {


    @Autowired
    public RecommendTeacherDao recommendTeacherDao;
    @Autowired
    public RecommendTeacherService recommendTeacherService;
    @Autowired
    public ProfService profService;
    @Autowired
    public EntityManager entityManager;

    public List<RecommendTeacher> findByCriteria(RecommendTeacherVo recommendTeacherVo) {
        String query = "SELECT e FROM RecommendTeacher e WHERE 1=1";
        if (recommendTeacherVo.getNom() != null) {
            query += " AND  e.nom LIKE '%" + recommendTeacherVo.getNom() + "%'";
        }
        if (recommendTeacherVo.getPrenom() != null) {
            query += "  AND  e.prenom LIKE '%" + recommendTeacherVo.getPrenom() + "'";
        }


        return entityManager.createQuery(query).getResultList();
    }

    public void update(RecommendTeacher recommendTeacher) {
        recommendTeacherDao.save(recommendTeacher);
    }

    public List<RecommendTeacher> findAll() {
        return recommendTeacherDao.findAll();
    }

    public int save(RecommendTeacher recommendTeacher) {
        if (findRecommendTeacherById(recommendTeacher.getId()) != null) {
            return -1;
        } else {
            System.out.println("id::: " + recommendTeacher.getId());

            recommendTeacherDao.save(recommendTeacher);
            return 1;
        }

    }


    public Optional<RecommendTeacher> findById(Long id) {
        return recommendTeacherDao.findById(id);
    }

    public RecommendTeacher findRecommendTeacherById(Long id) {
        return recommendTeacherDao.findRecommendTeacherById(id);
    }

    public List<RecommendTeacher> findRecommendTeacherByProfId(Long id) {
        return recommendTeacherDao.findRecommendTeacherByProfId(id);
    }


}