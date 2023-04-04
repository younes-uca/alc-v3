package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.ScheduleProf;
import ma.learn.quiz.service.impl.ScheduleProfService;
import ma.learn.quiz.vo.SchdeduleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/scheduleProf")
public class ScheduleProfAdminRest {

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

	@PostMapping("/all/")
	public List<ScheduleProf> findByCriteriaStudent(@RequestBody ScheduleProf schedule)
	{
		return scheduleProfService.findByCriteriaStudent(schedule);
	}

	@GetMapping("/")
	public List<ScheduleProf> findAll() {
		return scheduleProfService.findAll();
	}


	@DeleteMapping("/id/{id}")
	public void deleteScheduleProfById(@PathVariable Long id) {
		 scheduleProfService.deleteScheduleProfById(id);
	}

	@Autowired
	private ScheduleProfService scheduleProfService;
}
