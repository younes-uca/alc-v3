package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.*;
import ma.learn.quiz.dao.*;
import ma.learn.quiz.exception.SessionAlreadyExistException;
import ma.learn.quiz.service.Util.UtilString;
import ma.learn.quiz.service.vo.SessionCoursVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class SessionCoursService extends AbstractService {
    @Autowired
    public EntityManager entityManager;
    @Autowired
    private SessionCoursDao sessionCoursDao;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private ProfService profService;
    @Autowired
    private CoursService coursService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private SalaryDao salaryDao;
    @Autowired
    private WorkloadBonusService workloadBonusService;
    @Autowired
    private PaiementService paiementService;
    @Autowired
    private ClassAverageBonusService classAverageBonusService;
    @Autowired
    private WorkloadBonusProfService workloadBonusProfService;
    @Autowired
    private WorkloadBonusProfDao workloadBonusProfDao;
    @Autowired
    private ClassAverageBonusProfDao classAverageBonusProfDao;
    @Autowired
    private GroupeEtudiantService groupeEtudiantService;
    @Autowired
    private GroupeEtudiantDao groupeEtudiantDao;
    @Autowired
    private SectionService sectionService;

    public List<SessionCours> findByCriteria(SessionCours sessionCours) {
        String query = "SELECT e FROM SessionCours e WHERE 1=1";
        if (sessionCours.getProf().getNom() != null) {
            query += " AND  e.prof.nom LIKE '%" + sessionCours.getProf().getNom() + "%'";
        }
        if (sessionCours.getGroupeEtudiant().getLibelle() != null) {
            query += "  AND  e.groupeEtudiant.libelle LIKE '%" + sessionCours.getGroupeEtudiant().getLibelle() + "'";
        }


        return entityManager.createQuery(query).getResultList();
    }


    public List<SessionCours> findAllByCriteria(SessionCoursVO sessionCoursVO) {
        String query = this.init("SessionCours");
        if (sessionCoursVO.getEtudiant() != null) {
            query += this.addCriteria("etudiant.nom", sessionCoursVO.getEtudiant().getNom(), "LIKE");
        }
        if (sessionCoursVO.getReference() != null) {
            query += this.addCriteria("reference", sessionCoursVO.getReference(), "LIKE");
        }
        if (sessionCoursVO.getDateFin() != null) {
            query += this.addCriteria("dateFin", sessionCoursVO.getDateFin(), "LIKE");
        }
        if (sessionCoursVO.getCours().getLibelle() != null) {
            query += this.addCriteria("cours.libelle", sessionCoursVO.getCours().getLibelle(), "LIKE");
        }
        if (sessionCoursVO.getProf().getNom() != null) {
            query += this.addCriteria("prof.nom", sessionCoursVO.getProf().getNom(), "LIKE");
        }
        System.out.println("query = " + query);
        return entityManager.createQuery(query).getResultList();
    }


    public SessionCours findSessionCoursById(Long id) {
        return sessionCoursDao.findSessionCoursById(id);
    }

    public List<SessionCours> findByGroupeEtudiantId(Long id) {
        return sessionCoursDao.findByGroupeEtudiantId(id);
    }

    public int saveSessionByProf(SessionCours sessionCours) {
        SessionCours session = this.sessionCoursDao.save(sessionCours);
        for (Section s : sessionCours.getSections()
        ) {
            s.setSessionCours(session);
            this.sectionService.create(s);
        }
        return 1;
    }


    public int save(Long profid, Long groupEtudiantid, Long coursid) throws SessionAlreadyExistException {
        Optional<SessionCours> coursOptional = sessionCoursDao.findSessionCoursByProfIdAndCoursIdAndGroupeEtudiantId(profid, coursid, groupEtudiantid);
        if (coursOptional.isPresent()) {
            throw new SessionAlreadyExistException("Course is already finished.");
        } else {
            Date date = new Date();
            LocalDate localDate1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate1.getMonthValue();
            int annee = localDate1.getYear();
            Prof prof = profService.findProfById(profid);
            Cours cours = coursService.findCoursById(coursid);
            GroupeEtudiant groupeEtudiant = groupeEtudiantService.findGroupeEtudiantById(groupEtudiantid);
            if (prof == null || groupeEtudiant == null || cours == null) {
                return -1;
            } else {
                Salary salary = salaryDao.findSalaryByMoisAndAnneeAndProfId(month, annee, prof.getId());
                if (salary == null) {
                    salary = new Salary();
                    salary.setProf(prof);
                    salary.setAnnee(annee);
                    salary.setMois(month);
                    salary.setCode(UtilString.generateStringUppercaseAndLowercase(6));
                    salary.setNbrSessionMensuel(new BigDecimal(0));
                    salary.setTotalPayment(new BigDecimal(0));
                    salary.setTotalBonusClassAverage(new BigDecimal(0));
                    salary.setTotalBonusWorkload(new BigDecimal(0));
                    salary.setPayer(false);
                    salary = salaryDao.save(salary);
                    this.addSessionCours(prof.getId(), cours.getId(), groupeEtudiant.getId(), salary.getId(), date);
                    this.calculBonus(date, salary.getId());
                    return 1;
                } else {
                    this.addSessionCours(prof.getId(), cours.getId(), groupeEtudiant.getId(), salary.getId(), date);
                    this.calculBonus(date, salary.getId());
                    return 2;
                }
            }
        }
    }

    public void calculBonus(Date date, Long idSalary) {
        Salary salary = salaryDao.findSalaryById(idSalary);
        if (salary != null) {
            LocalDate localDate1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate1.getMonthValue();
            int annee = localDate1.getYear();
            List<WorkloadBonus> workloadBonusList = workloadBonusService.findAll();
            List<ClassAverageBonus> classAverageBonusList = classAverageBonusService.findAll();
            List<SessionCours> sessionCoursList = findAllSessionCoursByProfIdAndCurrentDate(salary.getProf().getId());
            List<GroupeEtudiant> groupeEtudiants = groupeEtudiantDao.findGroupeEtudiantByProfId(salary.getProf().getId());
            for (WorkloadBonus workloadBonus : workloadBonusList) {
                System.out.println(workloadBonus.getNombreSession());
                System.out.println(sessionCoursList.size());
                if (workloadBonus.getNombreSession() == sessionCoursList.size()) {
                    WorkloadBonusProf workloadBonusProf = workloadBonusProfDao.findWorkloadBonusProfBySalaryId(salary.getId());
                    salary.setTotalBonusWorkload(workloadBonus.getPrix());
                    salaryDao.save(salary);
                    if (workloadBonusProf == null) {
                        workloadBonusProf = new WorkloadBonusProf();
                        workloadBonusProf.setWorkloadBonus(workloadBonus);
                        workloadBonusProf.setProf(salary.getProf());
                        workloadBonusProf.setMois(month);
                        workloadBonusProf.setAnnee(annee);
                        workloadBonusProf.setSalary(salary);
                        workloadBonusProfDao.save(workloadBonusProf);
                    } else {
                        workloadBonusProf.setWorkloadBonus(workloadBonus);
                        workloadBonusProfDao.save(workloadBonusProf);
                    }

                }
            }
            for (ClassAverageBonus classAverageBonus : classAverageBonusList) {
                int nbr = sessionCoursList.size() / groupeEtudiants.size();
                if (classAverageBonus.getNombreSession() == nbr) {
                    ClassAverageBonusProf classAverageBonusProf = classAverageBonusProfDao.findClassAverageBonusProfBySalaryId(salary.getId());
                    salary.setTotalBonusClassAverage(classAverageBonus.getPrix());
                    salaryDao.save(salary);
                    if (classAverageBonusProf == null) {
                        classAverageBonusProf = new ClassAverageBonusProf();
                        classAverageBonusProf.setClassAverageBonus(classAverageBonus);
                        classAverageBonusProf.setProf(salary.getProf());
                        classAverageBonusProf.setMois(month);
                        classAverageBonusProf.setAnnee(annee);
                        classAverageBonusProf.setSalary(salary);
                        classAverageBonusProfDao.save(classAverageBonusProf);
                    } else {
                        classAverageBonusProf.setClassAverageBonus(classAverageBonus);
                        classAverageBonusProfDao.save(classAverageBonusProf);
                    }
                }
            }
        }
    }

    public void addSessionCours(Long profid, Long coursid, Long groupeEtudiantid, Long salaryid, Date date) {

        Salary salary = salaryDao.findSalaryById(salaryid);
        if (salary != null) {
            Prof prof = profService.findProfById(profid);
            Cours cours = coursService.findCoursById(coursid);
            GroupeEtudiant groupeEtudiant = groupeEtudiantDao.findGroupeEtudiantById(groupeEtudiantid);
            SessionCours sessionCours = new SessionCours();
            sessionCours.setCours(cours);
            sessionCours.setProf(prof);
            sessionCours.setPayer(false);
            sessionCours.setSalary(salary);
            sessionCours.setReference(UtilString.generateStringUppercaseAndLowercase(6));
            sessionCours.setDateFin(date);
            sessionCours.setGroupeEtudiant(groupeEtudiant);
            sessionCoursDao.save(sessionCours);
            salary.setTotalPayment(salary.getTotalPayment().add(sessionCours.getProf().getCategorieProf().getLessonRate()));
            salary.setNbrSessionMensuel(salary.getNbrSessionMensuel().add(new BigDecimal(1)));
            salaryDao.save(salary);
        }
    }

    public List<SessionCours> findAllSessionCoursByProfIdAndCurrentDate(Long idprof) {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String month = String.valueOf(localDate.getMonthValue());
        String annee = String.valueOf(localDate.getYear());
        if (month.equals("1") || month.equals("2") || month.equals("3") || month.equals("4") || month.equals("5") || month.equals("6") || month.equals("7") || month.equals("8") || month.equals("9")) {
            String query = "SELECT c FROM SessionCours c WHERE 1=1 AND c.prof.id" + "=" + idprof + " AND c.dateFin LIKE '%" + annee + "-" + '0' + month + "%'";
            return entityManager.createQuery(query).getResultList();

        } else {
            String query = "SELECT c FROM SessionCours c WHERE 1=1 AND c.prof.id" + "=" + idprof + " AND c.dateFin LIKE '%" + annee + "-" + month + "%'";
            return entityManager.createQuery(query).getResultList();

        }

    }


    public int update(Long id) {
        SessionCours session = findSessionCoursById(id);
        if (session == null) {
            return -1;
        } else {
            session.setPayer(false);
            sessionCoursDao.save(session);
            return 1;
        }
    }

    public List<SessionCours> findByProfId(Long id) {
        return sessionCoursDao.findByProfId(id);
    }

    public List<SessionCours> findAll() {
        return sessionCoursDao.findAll();
    }


    @Transactional
    public int deleteSessionCoursById(Long id) {
        return sessionCoursDao.deleteSessionCoursById(id);
    }


    @Transactional
    public int deleteSessionCoursById(List<SessionCours> sessionCourss) {
        int res = 0;
        for (int i = 0; i < sessionCourss.size(); i++) {
            res += deleteSessionCoursById(sessionCourss.get(i).getId());
        }
        return res;
    }

    public SessionCours findSessionCoursByProfNom(String nom) {
        return sessionCoursDao.findSessionCoursByProfNom(nom);
    }

    public List<SessionCours> findSessionCoursBySalaryId(Long id) {
        return sessionCoursDao.findSessionCoursBySalaryId(id);
    }

    public List<SessionCours> findSessionCoursByProfIdAndSalaryId(Long idprof, Long idsalary) {
        return sessionCoursDao.findSessionCoursByProfIdAndSalaryId(idprof, idsalary);
    }
}
