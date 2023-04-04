package ma.learn.quiz.service.vo;

import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.bean.Prof;

public class PaiementVo {
    private Prof prof;


    private Etudiant etudiant;

    private String datePaiement;

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public String getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(String datePaiement) {
        this.datePaiement = datePaiement;
    }
}
