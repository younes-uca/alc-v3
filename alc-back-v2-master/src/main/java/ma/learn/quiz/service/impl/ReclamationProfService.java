package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Prof;
import ma.learn.quiz.bean.ReclamationProf;
import ma.learn.quiz.bean.TypeReclamationProf;
import ma.learn.quiz.dao.ReclamationProfDao;
import ma.learn.quiz.service.Util.UtilString;
import ma.learn.quiz.service.vo.ReclamationProfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
public class ReclamationProfService extends AbstractService {
    @Autowired
    private ReclamationProfDao reclamationProfDao;
    @Autowired
    private ProfService profService;
    @Autowired
    private TypeReclamationProfService typeReclamationProfService;
    @Autowired
    private AdminService adminService;
    @Autowired
    public EntityManager entityManager;

    public List<ReclamationProf> findAll() {
        return reclamationProfDao.findAll();
    }

    public ReclamationProf findReclamationProfById(Long id) {
        return reclamationProfDao.findReclamationProfById(id);
    }

    public int saveReclamationProf(ReclamationProf reclamationProf) {
        Prof prof = profService.findProfById(reclamationProf.getProf().getId());
        TypeReclamationProf typeReclamationProf1 = typeReclamationProfService.findTypeReclamationProfByCode(reclamationProf.getTypeReclamationProf().getCode());
        if (prof == null || typeReclamationProf1 == null) {
            return -1;
        } else {
            ReclamationProf reclamationProf1 = new ReclamationProf();
            reclamationProf1.setDateReclamation(new Date());
            reclamationProf1.setProf(prof);
            reclamationProf1.setTypeReclamationProf(typeReclamationProf1);
            reclamationProf1.setTraite(false);
            reclamationProf1.setObjetReclamationProf(reclamationProf.getObjetReclamationProf());
            reclamationProf1.setMessage(reclamationProf.getMessage());
            reclamationProf1.setCommentaireTraiteur(null);
            reclamationProf1.setReference(UtilString.generateStringNumber(6));
            reclamationProf.setPostView(false);
            reclamationProfDao.save(reclamationProf1);
            return 1;
        }
    }

    public int reponseReclamationProf(ReclamationProf reclamationProf1, Date dateTraitement) {
        ReclamationProf reclamationProf = reclamationProfDao.findReclamationProfById(reclamationProf1.getId());

        if (reclamationProf == null) {
            return -1;
        } else {
            reclamationProf.setTraite(reclamationProf1.getTraite());
            reclamationProf.setDateTraitement(dateTraitement);
            reclamationProf.setAdmin(reclamationProf1.getAdmin());
            reclamationProf.setCommentaireTraiteur(reclamationProf1.getCommentaireTraiteur());
            reclamationProf.setDateReponse(new Date());
            reclamationProfDao.save(reclamationProf);
            return 1;
        }
    }

    public int viewReclamationProf(Long idReclamationProf1) {
        ReclamationProf reclamationProf = reclamationProfDao.findReclamationProfById(idReclamationProf1);

        if (reclamationProf == null) {
            return -1;
        } else {
            reclamationProf.setPostView(true);
            reclamationProfDao.save(reclamationProf);
            return 1;
        }
    }

    public List<ReclamationProf> findReclamationProfByProfId(Long id) {
        return reclamationProfDao.findReclamationProfByProfId(id);
    }

    public ReclamationProf findReclamationProfByIdAndProfId(Long id, Long idprof) {
        return reclamationProfDao.findReclamationProfByIdAndProfId(id, idprof);
    }

    public List<ReclamationProf> findAllByCriteria(ReclamationProfVo reclamationProfVo) {
        String query = this.init("ReclamationProf");
        if (reclamationProfVo.getProf() != null) {
            query += this.addCriteria("prof.nom", reclamationProfVo.getProf().getNom(), "LIKE");
        }
        if (reclamationProfVo.getReference() != null) {
            query += this.addCriteria("reference", reclamationProfVo.getReference(), "LIKE");
        }
        if (reclamationProfVo.getTraite() != null) {
            query += this.addCriteria("traite", reclamationProfVo.getTraite());
        }
        if (reclamationProfVo.getDateReclamation() != null) {
            query += this.addCriteria("dateReclamation", reclamationProfVo.getDateReclamation(), "LIKE");
        }

        System.out.println("query = " + query);
        return entityManager.createQuery(query).getResultList();
    }

}
