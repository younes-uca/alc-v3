package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Prof;
import ma.learn.quiz.bean.TrancheHoraireProf;
import ma.learn.quiz.dao.TrancheHoraireProfDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TrancheHoraireProfService {
    @Autowired
    private TrancheHoraireProfDao trancheHoraireProfDao;
    public void save(Prof prof , List<TrancheHoraireProf> trancheHoraireProfList)
    {
        for (TrancheHoraireProf trancheHoraireProf : trancheHoraireProfList){
            trancheHoraireProf.setProf(prof);
            trancheHoraireProf.setProfsId(prof.getId());
            trancheHoraireProfDao.save(trancheHoraireProf);
        }
    }
    public TrancheHoraireProf edit(TrancheHoraireProf trancheHoraireProf){
        TrancheHoraireProf trancheHora = this.findTrancheHoraireProfById(trancheHoraireProf.getId());
        if (trancheHora == null){
            return trancheHoraireProfDao.save(trancheHoraireProf);
        } else {
            trancheHora.setDay(trancheHoraireProf.getDay());
            trancheHora.setEndHour(trancheHoraireProf.getEndHour());
            trancheHora.setStartHour(trancheHoraireProf.getStartHour());
            return this.trancheHoraireProfDao.save(trancheHora);
        }
    }

    public TrancheHoraireProf findTrancheHoraireProfById(Long id) {
        return trancheHoraireProfDao.findTrancheHoraireProfById(id);
    }

    @Transactional
    public int deleteTrancheHoraireProfById(Long id) {
        return trancheHoraireProfDao.deleteTrancheHoraireProfById(id);
    }

    public List<TrancheHoraireProf> findByProfId(Long id) {
        return trancheHoraireProfDao.findByProfId(id);
    }

    public List<TrancheHoraireProf> findAll() {
        return trancheHoraireProfDao.findAll();
    }
}
