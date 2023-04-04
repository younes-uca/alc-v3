package ma.learn.quiz.service.vo;

import ma.learn.quiz.bean.Etudiant;

public class ReclamationEtudiantVo {
    private String reference;

    private String traite;

    private Etudiant etudiant;
    private String dateReclamation;

    public String getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(String dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTraite() {
        return traite;
    }

    public void setTraite(String traite) {
        this.traite = traite;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
