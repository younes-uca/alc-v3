package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.SessionCours;
import ma.learn.quiz.exception.SessionAlreadyExistException;
import ma.learn.quiz.service.impl.SessionCoursService;
import ma.learn.quiz.service.vo.SessionCoursVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/session")
public class SessionCoursAdminRest {
    @GetMapping("/allSessionCours/id/{id}")

    public List<SessionCours> findSessionCoursBySalaryId(@PathVariable Long id) {
        return sessionCoursService.findSessionCoursBySalaryId(id);
    }

    @PostMapping("/{profid}/{etudiantid}/{coursid}")
    public int save(@PathVariable Long profid, @PathVariable Long etudiantid, @PathVariable Long coursid) throws SessionAlreadyExistException {
        return sessionCoursService.save(profid, etudiantid, coursid);
    }


    @GetMapping("/id/{id}")
    public SessionCours findSessionCoursById(@PathVariable Long id) {
        return sessionCoursService.findSessionCoursById(id);
    }

    @PostMapping("/search")
    public List<SessionCours> findByCriteria(@RequestBody SessionCours sessionCours) {
        return sessionCoursService.findByCriteria(sessionCours);
    }


    @GetMapping("/update/{id}")
    public int update(@PathVariable Long id) {
        return sessionCoursService.update(id);
    }

    @GetMapping("/prof/id/{id}")
    public List<SessionCours> findByProfId(@PathVariable Long id) {
        return sessionCoursService.findByProfId(id);
    }

    @GetMapping("/prof/nom/{nom}")

    public SessionCours findSessionCoursByProfNom(@PathVariable String nom) {
        return sessionCoursService.findSessionCoursByProfNom(nom);
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


    @PostMapping("/allByCriteria")
    public List<SessionCours> findAllByCriteria(@RequestBody SessionCoursVO sessionCoursVO) {
        return sessionCoursService.findAllByCriteria(sessionCoursVO);
    }

    @Autowired
    private SessionCoursService sessionCoursService;
}
