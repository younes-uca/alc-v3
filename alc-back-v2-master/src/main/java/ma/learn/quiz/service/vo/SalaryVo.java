package ma.learn.quiz.service.vo;

import ma.learn.quiz.bean.Prof;

import java.math.BigDecimal;

public class SalaryVo {
    private Prof prof;
    private String annee;
    private String mois;
    private BigDecimal nbrSessionMensuel;
    private BigDecimal montantMensuel;
    private String  code;
    private String payer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public BigDecimal getNbrSessionMensuel() {
        return nbrSessionMensuel;
    }

    public void setNbrSessionMensuel(BigDecimal nbrSessionMensuel) {
        this.nbrSessionMensuel = nbrSessionMensuel;
    }

    public BigDecimal getMontantMensuel() {
        return montantMensuel;
    }

    public void setMontantMensuel(BigDecimal montantMensuel) {
        this.montantMensuel = montantMensuel;
    }
}
