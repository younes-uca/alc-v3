package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.SessionCours;
import ma.learn.quiz.exception.SessionAlreadyExistException;
import ma.learn.quiz.service.impl.SessionCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prof/session")
public class SessionCoursProfRest {
    @GetMapping("/prof/nom/{nom}")

    public SessionCours findSessionCoursByProfNom(@PathVariable String nom) {
        return sessionCoursService.findSessionCoursByProfNom(nom);
    }

    @GetMapping("/prof/idprof/{idprof}")

    public List<SessionCours> findAllSessionCoursByProfIdAndCurrentDate(@PathVariable Long idprof) {
        return sessionCoursService.findAllSessionCoursByProfIdAndCurrentDate(idprof);
    }

    @GetMapping("/id/{id}")
    public SessionCours findSessionCoursById(@PathVariable Long id) {
        return sessionCoursService.findSessionCoursById(id);
    }

    @PostMapping("/search")
    public List<SessionCours> findByCriteria(@RequestBody SessionCours sessionCours) {
        return sessionCoursService.findByCriteria(sessionCours);
    }

    @GetMapping("/{profid}/{groupEtudiantid}/{coursid}")
    public int save(@PathVariable Long profid, @PathVariable Long groupEtudiantid, @PathVariable Long coursid) throws SessionAlreadyExistException {
        return sessionCoursService.save(profid, groupEtudiantid, coursid);
    }

    @PostMapping("/")
    public int saveSessionByProf(@RequestBody SessionCours sessionCours) {
        return sessionCoursService.saveSessionByProf(sessionCours);
    }

    @GetMapping("/groupeEtudiant/id/{id}")
    public List<SessionCours> findByGroupeEtudiantId(@PathVariable Long id) {
        return sessionCoursService.findByGroupeEtudiantId(id);
    }

    @GetMapping("/update/{id}")
    public int update(@PathVariable Long id) {
        return sessionCoursService.update(id);
    }

    @GetMapping("/prof/id/{id}")
    public List<SessionCours> findByProfId(@PathVariable Long id) {
        return sessionCoursService.findByProfId(id);
    }

    @GetMapping("/")
    public List<SessionCours> findAll() {
        return sessionCoursService.findAll();
    }

    @DeleteMapping("/id/{id}")
    public int deleteSessionCoursById(@PathVariable Long id) {
        return sessionCoursService.deleteSessionCoursById(id);
    }

    @PostMapping("/delete-multiple-by-id")
    public int deleteSessionCoursById(@RequestBody List<SessionCours> sessionCourss) {
        return sessionCoursService.deleteSessionCoursById(sessionCourss);
    }

    @GetMapping("/salryprof/{idprof}/{idsalary}")

    public List<SessionCours> findSessionCoursByProfIdAndSalaryId(@PathVariable Long idprof, @PathVariable Long idsalary) {
        return sessionCoursService.findSessionCoursByProfIdAndSalaryId(idprof, idsalary);
    }

    @Autowired
    private SessionCoursService sessionCoursService;
}
