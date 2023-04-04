package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.RecommendTeacher;
import ma.learn.quiz.service.impl.RecommendTeacherService;
import ma.learn.quiz.service.vo.RecommendTeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prof/teacher")
public class RecommendTeacherProfRest {
    @Autowired
    private RecommendTeacherService recommendTeacherService;


    @GetMapping("/id/{id}")
    public Optional<RecommendTeacher> findById(Long id) {
        return recommendTeacherService.findById(id);
    }

    @GetMapping("/")
    public List<RecommendTeacher> findAll() {
        return recommendTeacherService.findAll();
    }

    @PostMapping("/search")
    public List<RecommendTeacher> findByCriteria(@RequestBody RecommendTeacherVo recommendTeacherVo) {
        return recommendTeacherService.findByCriteria(recommendTeacherVo);
    }

    @PostMapping("/")
    public int save(@RequestBody RecommendTeacher recommendTeacher) {
        return recommendTeacherService.save(recommendTeacher);
    }

    @PutMapping("/")
    public void update(@RequestBody RecommendTeacher recommendTeacher) {
        recommendTeacherService.update(recommendTeacher);
    }

    @GetMapping("/Prof/id/{id}")
    public List<RecommendTeacher> findRecommendTeacherByProfId(@PathVariable Long id) {
        return recommendTeacherService.findRecommendTeacherByProfId(id);
    }


}