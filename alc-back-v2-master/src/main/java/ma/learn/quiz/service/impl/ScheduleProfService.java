package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Cours;
import ma.learn.quiz.bean.GroupeEtudiant;
import ma.learn.quiz.bean.Prof;
import ma.learn.quiz.bean.ScheduleProf;
import ma.learn.quiz.dao.ScheduleProfDao;
import ma.learn.quiz.vo.SchdeduleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleProfService extends AbstractService {

    public ScheduleProf findByRef(String ref) {
        return scheduleProfDao.findByRef(ref);
    }

    public Optional<ScheduleProf> findById(Long aLong) {
        return scheduleProfDao.findById(aLong);
    }

    @Transactional
    public int deleteByRef(String ref) {
        return scheduleProfDao.deleteByRef(ref);
    }


    public ScheduleProf save(ScheduleProf scheduleProf) {
        if (scheduleProf.getId() == null || scheduleProf.getId() == 0) {
            GroupeEtudiant groupeEtudiant = groupeEtudiantService.findGroupeEtudiantById(scheduleProf.getGroupeEtudiant().getId());
            Prof prof = profService.findProfById(scheduleProf.getProf().getId());
            Cours cours = this.courseService.findCoursById(scheduleProf.getCours().getId());
            if (groupeEtudiant != null) {
                scheduleProf.setGroupeEtudiant(groupeEtudiant);
            } else {
                return null;
            }
            if (prof != null) {
                scheduleProf.setProf(prof);
            } else {
                return null;
            }

            if (cours != null) {
                scheduleProf.setCours(cours);
            } else {
                return null;
            }
            scheduleProf.setProfsId(prof.getId());
            return scheduleProfDao.save(scheduleProf);
        } else {

            return this.update(scheduleProf);
        }

    }

    public int saveAll(ScheduleProf scheduleProf) {
        if (findByRef(scheduleProf.getRef()) != null) {
            return -1;
        } else {
            scheduleProfDao.save(scheduleProf);
            return 1;
        }
    }

    public List<SchdeduleVo> findSchedule() {
        List<ScheduleProf> sheduls = scheduleProfDao.findAll();
        List<SchdeduleVo> schdeduleVos = new ArrayList<SchdeduleVo>();
        for (ScheduleProf s : sheduls) {
            SchdeduleVo schdeduleVo = new SchdeduleVo();
            schdeduleVo.setId(s.getId());
            schdeduleVo.setTitle(s.getGroupeEtudiant().getLibelle());
            schdeduleVo.setStart(s.getStartTime());
            schdeduleVo.setEnd(s.getEndTime());
            schdeduleVo.setRef(s.getRef());
            schdeduleVos.add(schdeduleVo);
        }
        return schdeduleVos;
    }

    public List<ScheduleProf> findAll() {
        return scheduleProfDao.findAll();
    }

    public ScheduleProf update(ScheduleProf scheduleProf) {
        return scheduleProfDao.save(scheduleProf);
    }


    public List<ScheduleProf> findByProfId(Long id) {
        return scheduleProfDao.findByProfIdOrderByCoursNumeroOrder(id);
    }

    public List<ScheduleProf> findByGroupeEtudiantId(Long id) {
        return scheduleProfDao.findByGroupeEtudiantIdOrderByCoursNumeroOrder(id);
    }

    public List<ScheduleProf> findByCriteriaStudent(ScheduleProf schedule) {
        String query = this.init("ScheduleProf");
        if (schedule.getGroupeEtudiant() != null) {
            query += this.addCriteria("groupeEtudiant.libelle", schedule.getGroupeEtudiant().getLibelle(), "LIKE");
        }
        if (schedule.getProf() != null) {
            if (schedule.getProf().getNom() != null) {
                query += this.addCriteria("prof.nom", schedule.getProf().getNom(), "LIKE");
            }
            if (schedule.getProf().getPrenom() != null) {
                query += this.addCriteria("prof.prenom", schedule.getProf().getPrenom(), "LIKE");
            }
            if (schedule.getProf().getUsername() != null) {
                query += this.addCriteria("prof.username", schedule.getProf().getUsername(), "LIKE");
            }

        }

        System.out.println("query = " + query);
        return entityManager.createQuery(query).getResultList();
    }

    public void deleteScheduleProfById(Long id) {
        scheduleProfDao.deleteById(id);
    }


    @Autowired
    private ScheduleProfDao scheduleProfDao;
    @Autowired
    private GroupeEtudiantService groupeEtudiantService;
    @Autowired
    private ProfService profService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CoursService courseService;
}
