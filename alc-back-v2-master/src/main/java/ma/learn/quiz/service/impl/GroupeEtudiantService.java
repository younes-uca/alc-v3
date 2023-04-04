package ma.learn.quiz.service.impl;


import ma.learn.quiz.bean.GroupeEtude;
import ma.learn.quiz.bean.GroupeEtudiant;
import ma.learn.quiz.bean.Parcours;
import ma.learn.quiz.dao.GroupeEtudiantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroupeEtudiantService extends AbstractService {
    @Autowired
    private GroupeEtudiantDao groupeEtudiantDao;
    @Autowired
    private GroupeEtudeService groupeEtudeService;
    @Autowired
    private ParcoursService parcoursService;
    @Autowired
    private GroupeEtudiantDetailService groupeEtudiantDetailService;
    @Autowired
    public EntityManager entityManager;
    @Autowired
    public ProfService profService;


    public List<GroupeEtudiant> findGroupeEtudiantByProfId(Long id) {
        return groupeEtudiantDao.findGroupeEtudiantByProfId(id);
    }

    public GroupeEtudiant save(GroupeEtudiant groupeEtudiant) throws Exception {
        Parcours parcours = parcoursService.findParcoursById(groupeEtudiant.getParcours().getId());
        if (parcours == null) {
            throw new Exception("Level not found");
        }
        GroupeEtude groupeEtude = groupeEtudeService.findGroupeEtudeById(groupeEtudiant.getGroupeEtude().getId());

        if (groupeEtude == null) {
            throw new Exception("Group option not found !");
        }
        groupeEtudiantDao.save(groupeEtudiant);
        groupeEtudiantDetailService.save(groupeEtudiant, groupeEtudiant.getGroupeEtudiantDetails());
        return groupeEtudiant;
    }

    public GroupeEtudiant findGroupeEtudiantById(Long id) {
        return groupeEtudiantDao.findGroupeEtudiantById(id);
    }

    public int update(GroupeEtudiant groupeEtudiant) {
        groupeEtudiantDao.save(groupeEtudiant);
        groupeEtudiantDetailService.save(groupeEtudiant, groupeEtudiant.getGroupeEtudiantDetails());
        return 1;
    }

    public GroupeEtudiant findByLibelle(String libelle) {
        return groupeEtudiantDao.findByLibelle(libelle);
    }

    @Transactional
    public int deleteGroupeEtudiantById(Long id) {
        int x = groupeEtudiantDetailService.deleteByGroupeEtudiantId(id);
        int y = groupeEtudiantDao.deleteGroupeEtudiantById(id);
        return x + y;
    }

    @Transactional
    public int deleteGroupeEtudiantById(List<GroupeEtudiant> groupeEtudiant) {
        int res1 = 0;
        int res2 = 0;

        for (int i = 0; i < groupeEtudiant.size(); i++) {
            res1 += deleteGroupeEtudiantById(groupeEtudiant.get(i).getId());
            res2 += groupeEtudiantDetailService.deleteByGroupeEtudiantId(groupeEtudiant.get(i).getId());
        }
        return res1 + res2;
    }
    public List<GroupeEtudiant> findAll() {
        return groupeEtudiantDao.findAll();
    }


    public List<GroupeEtudiant> findByParcoursIdAndNombrePlacevideGreaterThan(Long id, Long nombrePlacevide) {
        return groupeEtudiantDao.findByParcoursIdAndNombrePlacevideGreaterThan(id, nombrePlacevide);
    }


    public List<GroupeEtudiant> findByCriteria(GroupeEtudiant groupeEtudiant) {
        String query = this.init("GroupeEtudiant");
        if (groupeEtudiant.getParcours() != null) {
            if (groupeEtudiant.getParcours().getLibelle() != null) {
                query += this.addCriteria("parcours.libelle", groupeEtudiant.getParcours().getLibelle(), "LIKE");
            }
            if (groupeEtudiant.getLibelle() != null) {
                query += this.addCriteria("libelle", groupeEtudiant.getLibelle(), "LIKE");
            }
        }
        System.out.println("query = " + query);
        System.out.println(entityManager.createQuery(query).getResultList().size());
        return entityManager.createQuery(query).getResultList();
    }

}
