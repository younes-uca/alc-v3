package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.Section;
import ma.learn.quiz.util.SynchronizationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prof/synchronization")
public class SynchronizationRest {

    @Autowired
    private SynchronizationHandler synchronizationHandler;

    @PostMapping("/id/{id}")
    public int saveCurrentSectionByProfId(@PathVariable Long id, @RequestBody Section currentSection) {
        return synchronizationHandler.saveCurrentSectionByProfId(id, currentSection);
    }


    @PostMapping("/update/{id}")
    public int updateCurrentSectionByProfId(@PathVariable Long id, @RequestBody Section updatedSection) {
        return synchronizationHandler.updateCurrentSectionByProfId(id, updatedSection);
    }

    @GetMapping("/id/{id}")
    public Section getcurrentSectionByProf(@PathVariable Long id) {
        return synchronizationHandler.getcurrentSectionByProf(id);
    }

    @GetMapping("/remove/{id}")
    public int removeSessionWhenFinish(@PathVariable Long id) {
        return synchronizationHandler.removeSessionWhenFinish(id);
    }
}
