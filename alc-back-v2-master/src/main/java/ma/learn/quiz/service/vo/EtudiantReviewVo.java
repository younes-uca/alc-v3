package ma.learn.quiz.service.vo;

import ma.learn.quiz.bean.Cours;
import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.bean.Prof;

public class EtudiantReviewVo {
    private Etudiant etudiant;
    private Prof prof;
    private Cours cours;

    private String dateReview;

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public String getDateReview() {
        return dateReview;
    }

    public void setDateReview(String dateReview) {
        this.dateReview = dateReview;
    }
}
