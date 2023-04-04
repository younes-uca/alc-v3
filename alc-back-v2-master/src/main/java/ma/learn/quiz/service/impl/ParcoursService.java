package ma.learn.quiz.service.impl;


import ma.learn.quiz.bean.Centre;
import ma.learn.quiz.bean.Cours;
import ma.learn.quiz.bean.Parcours;
import ma.learn.quiz.bean.Section;
import ma.learn.quiz.dao.ParcoursDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParcoursService {
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private ParcoursDao parcoursDao;
    @Autowired
    private CoursService coursService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private CentreService centreService;


    public Parcours findParcoursById(Long id) {
        return parcoursDao.findParcoursById(id);
    }

    public Parcours findById(Long aLong) {
        Optional<Parcours> p = parcoursDao.findById(aLong);
        if (p.isPresent()) return p.get();
        else throw new RuntimeException("Level not found!");
    }

    public List<Parcours> findByCode(String code) {
        return parcoursDao.findByCode(code);
    }


    @Transactional
    public int deleteParcoursById(List<Parcours> parcourss) {
        int res = 0;
        for (int i = 0; i < parcourss.size(); i++) {
            res += deleteParcoursById(parcourss.get(i).getId());
        }
        return res;
    }


    @Transactional
    public int deleteParcoursById(Long id) {
        int deleteByEtudiantID = etudiantService.deleteByParcoursId(id);
        List<Cours> cours = coursService.findByParcoursId(id);
        for (Cours c : cours) {
            List<Section> sections = sectionService.findByCours(c);
            int sectionDeleted = 0;
            for (Section section : sections) {
                sectionDeleted += sectionService.deleteByCours(c);
            }
        }
        int deleteByCoursID = coursService.deleteByParcoursId(id);
        int deleteByID = parcoursDao.deleteParcoursById(id);
        return deleteByEtudiantID + deleteByCoursID + deleteByID;
    }


    public Parcours findParcoursByLibelle(String libelle) {
        return parcoursDao.findParcoursByLibelle(libelle);
    }


    public int save(Parcours parcours) throws Exception {
        Centre centre = centreService.findByRef(parcours.getCentre().getRef());
        if (centre == null) {
            return -1;
        } else {
            if (parcours.getCode().equals("true")) {
                Parcours parcoursTrialLesson = new Parcours();
                parcoursTrialLesson.setLibelle("Free " + parcours.getLibelle());
                parcoursTrialLesson.setCentre(parcours.getCentre());
                parcoursTrialLesson.setNombreCours(3);
                parcoursTrialLesson.setCode("FREE");
                parcoursTrialLesson.setDescription(parcours.getDescription());
                parcoursTrialLesson.setDateCreation(parcours.getDateCreation());
                Parcours p1 = parcoursDao.save(parcoursTrialLesson);
                for (int i = 0; i < parcoursTrialLesson.getNombreCours(); i++) {
                    Cours cours = new Cours();
                    cours.setNumeroOrder(i);
                    cours.setLibelle(this.courseList.get(i));
                    cours.setParcours(p1);
                    coursService.save(cours);
                }
            }
            int totalCoursesCreated = 0;
            parcours.setCentre(centre);
            parcours.setCode("PREMIUM");
            Parcours p = parcoursDao.save(parcours);
            for (int i = 0; i < parcours.getNombreCours(); i++) {
                Cours cours = new Cours();
                cours.setNumeroOrder(i);
                cours.setLibelle(this.courseList.get(i));
                cours.setParcours(p);
                totalCoursesCreated += 1;
                coursService.save(cours);
            }
            if (totalCoursesCreated == parcours.getNombreCours()) {
                return 1;
            } else return -2;
        }
    }

    public int create(Parcours parcours) {
        Centre centre = centreService.findByRef(parcours.getCentre().getRef());

        if (centre == null) {
            return -1;
        } else {
            parcours.setCentre(centre);
            parcoursDao.save(parcours);
            return 1;
        }

    }

    public List<Parcours> findByCentreRef(String ref) {
        return parcoursDao.findByCentreRef(ref);
    }

    public int deleteByCentreRef(String Ref) {
        return parcoursDao.deleteByCentreRef(Ref);
    }

    public List<Parcours> findAll() {
        return parcoursDao.findAll();
    }

    public void delete(Parcours entity) {
        parcoursDao.delete(entity);
    }


    public Parcours update(Parcours parcours) {
        List<Cours> cours = coursService.findByParcoursId(parcours.getId());
        int nbCours = 0;
        for (Cours c : cours) {
            if (c.getId() != null) {
                nbCours++;
            }

        }
        parcours.setNombreCours(nbCours);
        Centre centre = centreService.findByRef(parcours.getCentre().getRef());
        parcours.setCentre(centre);
        parcours.setLibelle(parcours.getLibelle());
        parcours.setDateCreation(parcours.getDateCreation());
        parcours.setDescription(parcours.getDescription());
        parcours.setDatePublication(parcours.getDatePublication());
        parcours.setCourses(parcours.getCourses());
        return parcoursDao.save(parcours);

    }

    List<String> courseList = new ArrayList<String>() {{
        add("Intro lesson");
        add("1 Saying Hello!");
        add("2 Hey! What's up?");
        add("3 Las get acquainted");
        add("4 Let me introduce myself");
        add("5 Meeting people");
        add("6 More people to know");
        add("7 Family and friends");
        add("8 Et More relatives?");
        add("9 Same or different");
        add("10 More in common");
        add("11 Food you have");
        add("12 Grab a bite");
        add("13 Home sweet home");
        add("14 No place like home");
        add("15 A day in a life");
        add("16 Just another day");
        add("17 Leisure activities");
        add("18 When you're free");
        add("19 Personal profile");
        add("20 More about you");
        add("21 Feel good");
        add("22 Get emotional");
        add("23 Events");
        add("24 More occasions");
        add("25 Technology");
        add("26 For the geeks");
        add("27 Health Care");
        add("28 Feeling well");
        add("29 Celebration");
        add("30 More to celebrate");
        add("31 History");
        add("32 Past");
        add("33 Inventions");
        add("34 Innovations");
        add("35 Weather");
        add("36 More about weather");
        add("37 Dreams");
        add("38 Dreams and ambitions");
        add("39 Education and careers");
        add("40 Looming and working");
        add("41 Transport");
        add("42 Traffic");
        add("43 Adventures");
        add("44 Quests");
        add("45 Character");
        add("46 Personality");
        add("47 Follow the fashion");
        add("48 Fashion trends");
        add("49 Culture");
        add("50 Traditions and customs");
    }};


}
