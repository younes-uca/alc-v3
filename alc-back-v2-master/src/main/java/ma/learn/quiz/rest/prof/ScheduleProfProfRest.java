package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.ScheduleProf;
import ma.learn.quiz.service.impl.ScheduleProfService;
import ma.learn.quiz.vo.SchdeduleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prof/scheduleProf")
public class ScheduleProfProfRest {


    @GetMapping("/id/{id}")
    public ScheduleProf findByScheduleId(@PathVariable Long id) {
        return scheduleProfService.findById(id).get();
    }


    @PostMapping("/save/")
    public int saveAll(ScheduleProf scheduleProf) {
        return scheduleProfService.saveAll(scheduleProf);
    }

    @GetMapping("/vo/")
    public List<SchdeduleVo> findSchedule() {
        return scheduleProfService.findSchedule();
    }


    @GetMapping("/ref/{ref}")
    public ScheduleProf findByRef(@PathVariable String ref) {
        return scheduleProfService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return scheduleProfService.deleteByRef(ref);
    }

    @PostMapping("/")
    public ScheduleProf save(@RequestBody ScheduleProf scheduleProf) {
        return scheduleProfService.save(scheduleProf);
    }

    @GetMapping("/")
    public List<ScheduleProf> findAll() {
        return scheduleProfService.findAll();
    }


    @GetMapping("/prof/id/{id}")
    public List<ScheduleProf> findByProfId(@PathVariable Long id) {
        return scheduleProfService.findByProfId(id);
    }

    @PostMapping("/all/")
    public List<ScheduleProf> findByCriteriaStudent(@RequestBody ScheduleProf schedule) {
        return scheduleProfService.findByCriteriaStudent(schedule);
    }

    @Autowired
    private ScheduleProfService scheduleProfService;
}
