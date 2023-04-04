package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.bean.GroupeEtudiant;
import ma.learn.quiz.bean.GroupeEtudiantDetail;
import ma.learn.quiz.dao.GroupeEtudiantDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroupeEtudiantDetailService extends AbstractService {
    @Autowired
    private GroupeEtudiantDetailDao groupeEtudiantDetailDao;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    public EntityManager entityManager;

    public void save(GroupeEtudiant groupeEtudiant, List<GroupeEtudiantDetail> groupeEtudiantDetails) {
        for (GroupeEtudiantDetail groupeEtudiantDetail : groupeEtudiantDetails) {
            GroupeEtudiantDetail grpExist = groupeEtudiantDetailDao.findByGroupeEtudiantIdAndEtudiantId(groupeEtudiant.getId(),
                    groupeEtudiantDetail.getEtudiant().getId());

            if (grpExist == null) {
                groupeEtudiantDetail.setGroupeEtudiant(groupeEtudiant);
                groupeEtudiantDetailDao.save(groupeEtudiantDetail);
            }
        }
    }

    public void save(GroupeEtudiant groupeEtudiant, GroupeEtudiantDetail groupeEtudiantDetail) {
        Etudiant etudiant = etudiantService.findEtudiantById(groupeEtudiantDetail.getEtudiant().getId());
        groupeEtudiantDetail.setEtudiant(etudiant);
        groupeEtudiantDetail.setGroupeEtudiant(groupeEtudiant);
        groupeEtudiantDetailDao.save(groupeEtudiantDetail);

    }

    public int deleteByGroupeEtudiantId(Long id) {
        return groupeEtudiantDetailDao.deleteByGroupeEtudiantId(id);
    }

    public void update(GroupeEtudiant groupeEtudiant, List<GroupeEtudiantDetail> groupeEtudiantDetails) {

        for (GroupeEtudiantDetail groupeEtudiantDetail : groupeEtudiantDetails) {
            Etudiant etudiant = etudiantService.findEtudiantById(groupeEtudiantDetail.getEtudiant().getId());

            //  groupeEtudeDetail.setEtudiants( findEtudiantByGroupeEtudeDetail(groupeEtudeDetail));
            groupeEtudiantDetail.setEtudiant(etudiant);
            groupeEtudiantDetail.setGroupeEtudiant(groupeEtudiant);
            groupeEtudiantDetailDao.save(groupeEtudiantDetail);
        }
    }

    public List<GroupeEtudiantDetail> findByGroupeEtudiantId(Long id) {
        return groupeEtudiantDetailDao.findByGroupeEtudiantId(id);
    }

    public List<GroupeEtudiantDetail> findByEtudiantId(Long id) {
        return groupeEtudiantDetailDao.findByEtudiantId(id);
    }

    @Transactional
    public int deleteGroupeEtudiantDetailById(Long id) {
        return groupeEtudiantDetailDao.deleteGroupeEtudiantDetailById(id);
    }


    public int deleteGroupeEtudiantDetailByEtudiantId(Long id) {
        return groupeEtudiantDetailDao.deleteGroupeEtudiantDetailByEtudiantId(id);
    }


    public List<GroupeEtudiantDetail> findByCriteria(GroupeEtudiantDetail groupeEtudiantDetail) {
        String query = this.init("GroupeEtudiantDetail");
        if (groupeEtudiantDetail.getEtudiant() != null) {
            if (groupeEtudiantDetail.getEtudiant().getNom() != null) {
                query += this.addCriteria("etudiant.nom", groupeEtudiantDetail.getEtudiant().getNom(), "LIKE");
            }
            if (groupeEtudiantDetail.getEtudiant().getPrenom() != null) {
                query += this.addCriteria("etudiant.prenom", groupeEtudiantDetail.getEtudiant().getPrenom(), "LIKE");
            }

        }
        if (groupeEtudiantDetail.getGroupeEtudiant() != null) {
            if (groupeEtudiantDetail.getGroupeEtudiant().getLibelle() != null) {
                query += this.addCriteria("groupeEtudiant.libelle", groupeEtudiantDetail.getGroupeEtudiant().getLibelle(), "LIKE");
            }

        }
        System.out.println("query = " + query);
        System.out.println(entityManager.createQuery(query).getResultList().size());
        return entityManager.createQuery(query).getResultList();
    }

}
