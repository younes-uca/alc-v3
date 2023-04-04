package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.ProfReview;
import ma.learn.quiz.dao.ProfReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProfReviewService {
    @Autowired
    private ProfReviewDao profReviewDao;

    public ProfReview findByReview(int review) {
        return profReviewDao.findByReview(review);
    }
@Transactional
    public int deleteByReview(int review) {
        return profReviewDao.deleteByReview(review);
    }

    public ProfReview findByEtudiantIdAndCoursIdAndProfId(long id, long ids, long idd) {
        return profReviewDao.findByEtudiantIdAndCoursIdAndProfId(id, ids, idd);
    }

    public int save(ProfReview profReview) {
        ProfReview prof = profReviewDao.findByEtudiantIdAndCoursIdAndProfId(profReview.getEtudiant().getId(),profReview.getCours().getId(), profReview.getProf().getId());
        if (profReview.getReview() == 0) {
            return -1;
        }else if (prof != null) {
            return -2;
        } else {
            profReviewDao.save(profReview);
            return 1;
        }
    }
}
