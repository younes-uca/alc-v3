package ma.learn.quiz.service.impl;


import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.bean.FreeTrialformule;
import ma.learn.quiz.bean.Inscription;
import ma.learn.quiz.dao.EtudiantDao;
import ma.learn.quiz.dao.FreeTrialformuleDao;
import ma.learn.quiz.dao.InscriptionDao;
import ma.learn.quiz.service.Util.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class FreeTrialFormuleService extends AbstractService {

    @Autowired
    private FreeTrialformuleDao freeTrialformuleDao;
    @Autowired
    private InscriptionDao inscriptionDao;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private EtudiantDao etudiantDao;

    public FreeTrialformule findByCode(String code) {
        return freeTrialformuleDao.findByCode(code);
    }

    public FreeTrialformule findByInscriptionId(Long id) {
        return freeTrialformuleDao.findByInscriptionId(id);
    }

    public int save(FreeTrialformule freeTrialformule){
        FreeTrialformule freeTrialformule1 =new FreeTrialformule();

        Inscription inscription = inscriptionDao.findInscriptionByEtudiantId(freeTrialformule.getInscription().getEtudiant().getId());
        Etudiant etudiant = etudiantService.findEtudiantById(inscription.getEtudiant().getId());
        if (inscription == null){
            return -1;
        }
        if (freeTrialformule.getId() != null){
             freeTrialformule1 = freeTrialformuleDao.findByCode(freeTrialformule.getCode());
        }
        etudiant.setTeacherLocality(freeTrialformule.getInscription().getEtudiant().getTeacherLocality());
        etudiant.setDateNaissance(freeTrialformule.getInscription().getEtudiant().getDateNaissance());
        etudiantDao.save(etudiant);


        inscription.setFonction(freeTrialformule.getInscription().getFonction());
        inscription.setInteretEtudiant(freeTrialformule.getInscription().getInteretEtudiant());
        inscription.setNiveauEtude(freeTrialformule.getInscription().getNiveauEtude());
        inscription.setStatutSocial(freeTrialformule.getInscription().getStatutSocial());
        inscription.setSkill(freeTrialformule.getInscription().getSkill());
        inscriptionDao.save(inscription);


        freeTrialformule1.setCode(UtilString.generateStringNumber(8));
        freeTrialformule1.setDayspeerweek(freeTrialformule.getDayspeerweek());
        freeTrialformule1.setTimeperday(freeTrialformule.getTimeperday());
        freeTrialformule1.setTeacherAgeRange(freeTrialformule.getTeacherAgeRange());
        freeTrialformule1.setTeacherGenderoption(freeTrialformule.getTeacherGenderoption());
        freeTrialformule1.setTeacherPersonnality(freeTrialformule.getTeacherPersonnality());
        freeTrialformule1.setInscription(inscription);
        freeTrialformule1.setStatus(false);
        freeTrialformuleDao.save(freeTrialformule1);
        return 1;
    }

    public List<FreeTrialformule> findByCriteria(FreeTrialformule freeTrialformuleVo){
        String query = this.init("FreeTrialformule");
        if (freeTrialformuleVo.getInscription() != null) {
            if (freeTrialformuleVo.getCode() != null) {
                query += this.addCriteria("code", freeTrialformuleVo.getCode(), "LIKE");
            }
            if (freeTrialformuleVo.getDayspeerweek() != null) {
                query += this.addCriteria("dayspeerweek", freeTrialformuleVo.getDayspeerweek(), "LIKE");
            }
            if (freeTrialformuleVo.getTimeperday() != null) {
                query += this.addCriteria("timeperday", freeTrialformuleVo.getTimeperday(), "LIKE");
            }
            if (freeTrialformuleVo.getDateConfirmation() != null) {
                query += this.addCriteria("dateConfirmation", freeTrialformuleVo.getDateConfirmation(), "LIKE");
            }
            if (freeTrialformuleVo.getTeacherAgeRange() != null) {
                query += this.addCriteria("teacherAgeRange", freeTrialformuleVo.getTeacherAgeRange(), "LIKE");
            }
            if (freeTrialformuleVo.getTeacherGenderoption() != null) {
                query += this.addCriteria("teacherGenderoption", freeTrialformuleVo.getTeacherGenderoption(), "LIKE");
            }
            if (freeTrialformuleVo.getTeacherPersonnality() != null) {
                query += this.addCriteria("teacherPersonnality", freeTrialformuleVo.getTeacherPersonnality(), "LIKE");
            }
            if (freeTrialformuleVo.isStatus() || !freeTrialformuleVo.isStatus() ) {
                query += this.addCriteria("status", freeTrialformuleVo.isStatus());
            }
            if (freeTrialformuleVo.getInscription().getEtudiant() != null) {
                query += this.addCriteria("inscription.etudiant.prenom", freeTrialformuleVo.getInscription().getEtudiant().getPrenom(), "LIKE");
            }
            if (freeTrialformuleVo.getInscription().getEtudiant() != null) {
                query += this.addCriteria("inscription.etudiant.username", freeTrialformuleVo.getInscription().getEtudiant().getUsername(), "LIKE");
            }
            if (freeTrialformuleVo.getInscription().getEtudiant() != null) {
                query += this.addCriteria("inscription.etudiant.numero", freeTrialformuleVo.getInscription().getEtudiant().getNumero(), "LIKE");
            }
        }
        System.out.println("query = " + query);
        System.out.println(entityManager.createQuery(query).getResultList().size());
        return entityManager.createQuery(query).getResultList();
    }
    @Autowired
    private EntityManager entityManager;

}
