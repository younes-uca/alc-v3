package ma.learn.quiz.service.vo;

import ma.learn.quiz.bean.Etudiant;

public class InviteStudentVo {
    private Etudiant etudiant;
    private String isAccepted;
    private String isPaidPack;
    private String emailInvited;
    private String code;
    private String dateSentInvitation;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }

    public String getIsPaidPack() {
        return isPaidPack;
    }

    public void setIsPaidPack(String isPaidPack) {
        this.isPaidPack = isPaidPack;
    }

    public String getEmailInvited() {
        return emailInvited;
    }

    public void setEmailInvited(String emailInvited) {
        this.emailInvited = emailInvited;
    }

    public String getDateSentInvitation() {
        return dateSentInvitation;
    }

    public void setDateSentInvitation(String dateSentInvitation) {
        this.dateSentInvitation = dateSentInvitation;
    }
}
