package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.SessionCours;
import ma.learn.quiz.service.impl.SessionCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/session")
public class SessionCoursPublicRest {
    @GetMapping("/prof//nom/{nom}")

    public SessionCours findSessionCoursByProfNom(@PathVariable String nom) {
        return sessionCoursService.findSessionCoursByProfNom(nom);
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

    @Autowired
    private SessionCoursService sessionCoursService;
}
