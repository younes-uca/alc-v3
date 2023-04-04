package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Centre;
import ma.learn.quiz.dao.CentreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class CentreService {
    @Autowired
    public CentreDao centredao;
    @Autowired
    public InscriptionService inscriptionService;
    @Autowired
    public ParcoursService parcoursService;
    @Autowired
    public EtudiantService etudiantService;
    @Autowired
    public EtatInscriptionService etatInscriptionService;

    public List<Centre> findAll() {
        return centredao.findAll();
    }

    public Centre findByLibelle(String libelle) {
        return centredao.findByLibelle(libelle);
    }

    @Transactional
    public int deleteByRef(String ref) {
        int resultatetat = etatInscriptionService.deleteByRef(ref);
        int resultatinscription = inscriptionService.deleteByRef(ref);
        int resultat1 = etudiantService.deleteByParcoursCode(ref);
        int resultatparcours = parcoursService.deleteByCentreRef(ref);
        int resultatcentre = centredao.deleteByRef(ref);
        return resultatetat + resultatinscription + resultatcentre + resultatparcours + resultat1;
    }

    public Centre findByRef(String ref) {
        return centredao.findByRef(ref);
    }

    public int save(Centre centre) {
        if (findByRef(centre.getRef()) != null) {
            return -1;
        } else {
            centredao.save(centre);
            return 1;
        }

    }
}
