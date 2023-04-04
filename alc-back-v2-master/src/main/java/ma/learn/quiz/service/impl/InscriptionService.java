package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.*;
import ma.learn.quiz.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class InscriptionService extends AbstractService {
    @Autowired
    public InscriptionDao inscriptionDao;
    @Autowired
    public EtatInscriptionService etatInscriptionService;
    @Autowired
    public ParcoursService parcoursService;
    @Autowired
    public ProfService profService;
    @Autowired
    public CentreService centreService;
    @Autowired
    public EtudiantService etudiantService;
    @Autowired
    public EntityManager entityManager;
    @Autowired
    public GroupeEtudiantService groupeEtudiantService;
    @Autowired
    private GroupeEtudeService groupeEtudeService;
    @Autowired
    private GroupeEtudiantDetailService groupeEtudiantDetailService;
    @Autowired
    private PackStudentService packStudentService;
    @Autowired
    private EtudiantDao etudiantDao;

    @Autowired
    public NiveauEtudeDao niveauEtudeDao;
    @Autowired
    public InteretEtudiantDao interetEtudiantDao;
    @Autowired
    public FonctionDao fonctionDao;
    @Autowired
    public StatutSocialDao statutSocialDao;
    @Autowired
    public InviteStudentService inviteStudentService;
    @Autowired
    public InviteStudentdDao inviteStudentdDao;


    public List<Inscription> findAllByEtatInscriptionLibelle(String libelle) {
        return inscriptionDao.findAllByEtatInscriptionLibelleOrderByIdDesc(libelle);
    }

    public List<Inscription> findByCriteria(Inscription inscription) {
        String query = this.init("Inscription");
        if (inscription.getEtudiant() != null) {
            if (inscription.getEtudiant().getNom() != null) {
                query += this.addCriteria("etudiant.nom", inscription.getEtudiant().getNom(), "LIKE");
            }
            if (inscription.getEtudiant().getPrenom() != null) {
                query += this.addCriteria("etudiant.prenom", inscription.getEtudiant().getPrenom(), "LIKE");
            }
            if (inscription.getEtudiant().getUsername() != null) {
                query += this.addCriteria("etudiant.username", inscription.getEtudiant().getUsername(), "LIKE");
            }
        }
        System.out.println("query = " + query);
        System.out.println(entityManager.createQuery(query).getResultList().size());
        return entityManager.createQuery(query).getResultList();
    }


    public int affecter(Parcours parcours, GroupeEtude groupeEtude, Etudiant etudiant) {
        System.out.println(parcours.getId());
        System.out.println(groupeEtude.getId());
        System.out.println(etudiant.getId());
        List<GroupeEtudiant> list = groupeEtudiantService.findByParcoursIdAndNombrePlacevideGreaterThan(parcours.getId(), 0L);
        if (list == null || list.isEmpty()) {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            String formattedDate = currentDate.format(formatter);

            GroupeEtudiant groupeEtudiant = new GroupeEtudiant();
            groupeEtudiant.setGroupeEtude(groupeEtude);
            groupeEtudiant.setParcours(parcours);
            if (groupeEtude.getNombreEtudiant() == 1) {
                groupeEtudiant.setLibelle(etudiant.getUsername());
            } else {
                groupeEtudiant.setLibelle(formattedDate);
            }
            groupeEtudiant.setGroupeEtudiantDetails(new ArrayList<>());
            GroupeEtudiantDetail groupeEtudiantDetail = new GroupeEtudiantDetail();
            groupeEtudiantDetail.setEtudiant(etudiant);
            groupeEtudiant.getGroupeEtudiantDetails().add(groupeEtudiantDetail);
            groupeEtudiant.setNombrePlace(groupeEtudiant.getGroupeEtude().getNombreEtudiant());
            groupeEtudiant.setNombrePlacevide(groupeEtudiant.getGroupeEtude().getNombreEtudiant() - 1);
            groupeEtudiant.setNombrePlaceNonVide(1L);
            try {
                groupeEtudiantService.save(groupeEtudiant);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;
        } else {
            GroupeEtudiant groupeEtudiant = list.get(0);
            groupeEtudiant.setNombrePlacevide(groupeEtudiant.getNombrePlacevide() - 1);
            groupeEtudiant.setNombrePlaceNonVide(groupeEtudiant.getNombrePlaceNonVide() + 1);
            GroupeEtudiantDetail groupeEtudiantDetail = new GroupeEtudiantDetail();
            groupeEtudiantDetail.setEtudiant(etudiant);
            groupeEtudiantDetailService.save(groupeEtudiant, groupeEtudiantDetail);
            return 2;
        }
    }

    public Inscription updateInsc(Inscription inscription) {
        return this.inscriptionDao.save(inscription);
    }

    public void validateInscription(Inscription inscription) {
        EtatInscription etatInscription = etatInscriptionService.findEtatInscriptionById(2L); // validated
        inscription.setEtatInscription(etatInscription);
        this.inscriptionDao.save(inscription);
    }

    public Etudiant save(Inscription inscription) throws Exception {
        if (inscription.getId() != null) {
            PackStudent packStudent = null;
            Parcours parcours = null;
            if (inscription.getPackStudent() != null) {
                packStudent = packStudentService.findById(inscription.getPackStudent().getId());
            }
            if (inscription.getParcours() != null) {
                parcours = parcoursService.findParcoursById(inscription.getParcours().getId());
            }

            EtatInscription etatInscription = etatInscriptionService.findEtatInscriptionById(inscription.getEtatInscription().getId());
            Etudiant etudiant = this.etudiantService.findEtudiantById(inscription.getEtudiant().getId());
            GroupeEtude groupeEtude = null;
            if (inscription.getGroupeEtude() != null) {
                groupeEtude = groupeEtudeService.findGroupeEtudeById(inscription.getGroupeEtude().getId());
            }
            inscription.setParcours(parcours);
            inscription.setEtatInscription(etatInscription);
            inscription.setEtudiant(etudiant);
            inscription.setGroupeEtude(groupeEtude);
            inscription.setPackStudent(packStudent);
            inscriptionDao.save(inscription);
            etudiant.setGroupeEtude(groupeEtude);
            etudiant.setParcours(parcours);
            etudiantDao.save(etudiant);
            if (parcours != null && groupeEtude != null) {
                affecter(parcours, groupeEtude, etudiant);
            }
            return etudiant;
        } else {
            EtatInscription etatInscription = etatInscriptionService.findEtatInscriptionById((long) 1);
            assert inscription.getId() != null;
            Etudiant etudiant = this.etudiantService.findEtudiantById(inscription.getEtudiant().getId());
            if (etudiant == null) {
                throw new Exception("Student not found.");
            } else {
                inscription.setEtatInscription(etatInscription);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                inscription.setDateRegistration(dtf.format(now));
                inscription.setEtudiant(etudiant);
                inscriptionDao.save(inscription);
                return inscription.getEtudiant();
            }
        }
    }

    public int updateByStudent(String packCode, Etudiant etudiant) {
        Inscription inscription = inscriptionDao.findInscriptionByEtudiantId(etudiant.getId());
        Parcours parcours = new Parcours();
        GroupeEtude groupeEtude = new GroupeEtude();
        if (etudiant.getParcours() != null) {
            parcours = parcoursService.findParcoursById(etudiant.getParcours().getId());
        }
        if (etudiant.getGroupeEtude() != null) {
            groupeEtude = groupeEtudeService.findGroupeEtudeById(etudiant.getGroupeEtude().getId());
        }
        PackStudent packStudent = packStudentService.findPackStudentByCode(packCode);
        Etudiant etudiant1 = etudiantService.findEtudiantById(etudiant.getId());
        if (inscription != null) {
            if (parcours.getId() != null) {
                inscription.setParcours(parcours);
            }
            if (groupeEtude.getId() != null) {
                inscription.setGroupeEtude(groupeEtude);
            }
            inscription.getEtudiant().setTeacherLocality(etudiant.getTeacherLocality());
            inscription.setPackStudent(packStudent);
            inscriptionDao.save(inscription);
            etudiant1.setGroupeEtude(inscription.getGroupeEtude());
            etudiant1.setParcours(inscription.getParcours());
            etudiant1.setTeacherLocality(etudiant.getTeacherLocality());
            etudiantDao.save(etudiant1);
            InviteStudent inviteStudent = inviteStudentService.findInviteStudentByEmailInvited(etudiant1.getUsername());
            if (inviteStudent != null) {
                inviteStudent.setAccepted(true);
                inviteStudent.setPaidPack(true);
                inviteStudent.setDateAcceptInvitation(new Date());
                inviteStudent.setDatePayPack(new Date());
                this.inviteStudentdDao.save(inviteStudent);

            }
            if (parcours.getId() != null && groupeEtude.getId() != null) {
                affecter(parcours, groupeEtude, etudiant);
            }
            return 1;
        } else {
            return -1;
        }
    }


    public Inscription findInscriptionByLogin(String login) {
        return inscriptionDao.findInscriptionByEtudiantUsername(login);
    }

    public int valider(Inscription inscription) {
        Parcours parcrs = this.parcoursService.findParcoursById(inscription.getParcours().getId());
        EtatInscription etatInscription = etatInscriptionService.findEtatInscriptionById(inscription.getEtatInscription().getId());
        Etudiant etudiant = etudiantService.findEtudiantById(inscription.getEtudiant().getId());
        NiveauEtude niveauEtude = niveauEtudeDao.findByCode(inscription.getNiveauEtude().getCode());
        StatutSocial statutSocial = statutSocialDao.findByCode(inscription.getStatutSocial().getCode());
        InteretEtudiant interetEtudiant = interetEtudiantDao.findByCode(inscription.getInteretEtudiant().getCode());
        Fonction fonction = fonctionDao.findByCode(inscription.getFonction().getCode());
        etudiant.setParcours(parcrs);
        inscription.setStatutSocial(inscription.getStatutSocial());
        inscription.setFonction(inscription.getFonction());
        inscription.setInteretEtudiant(inscription.getInteretEtudiant());
        inscription.setNiveauEtude(inscription.getNiveauEtude());
        inscription.setEtatInscription(etatInscription);
        inscription.setSkill(inscription.getSkill());
        inscription.setParcours(parcrs);
        inscriptionDao.save(inscription);
        etudiant.setStatutSocial(inscription.getStatutSocial());
        etudiant.setNiveauEtude(inscription.getNiveauEtude());
        etudiant.setFonction(inscription.getFonction());
        etudiant.setInteretEtudiant(interetEtudiant);
        etudiant.setSkill(inscription.getSkill());
        etudiantService.updateEtudiant(etudiant);
        return 0;
    }


    public List<Inscription> findAll() {
        return inscriptionDao.findAll();
    }


    @Transactional
    public int deleteInscriptionById(List<Inscription> inscription) {
        int res = 0;
        for (int i = 0; i < inscription.size(); i++) {
            res += deleteInscriptionById(inscription.get(i).getId());
        }
        return res;
    }

    @Transactional
    public int deleteInscriptionById(Long id) {
        return inscriptionDao.deleteInscriptionById(id);
    }

    public int deleteByRef(String ref) {
        return inscriptionDao.deleteByEtudiantRef(ref);
    }


    public Inscription findByNumeroInscription(int numeroInscription) {
        return inscriptionDao.findByNumeroInscription(numeroInscription);
    }

    public int deleteInscriptionByEtudiantId(Long id) {
        return inscriptionDao.deleteInscriptionByEtudiantId(id);
    }

    public Inscription findInscriptionByEtudiantId(Long id) {
        return inscriptionDao.findInscriptionByEtudiantId(id);
    }

    public List<Inscription> findAllByOrderByIdDesc() {
        return inscriptionDao.findAllByOrderByIdDesc();
    }
}
