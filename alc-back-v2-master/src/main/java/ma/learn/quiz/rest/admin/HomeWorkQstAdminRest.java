package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.HomeWorkQuestion;
import ma.learn.quiz.service.impl.HomeWorkQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/homeWorkQST")
public class HomeWorkQstAdminRest {
    @Autowired
    private HomeWorkQuestionService homeWorkQuestionService;

    @GetMapping("/homework/{id}")
    public List<HomeWorkQuestion> findHomeWorkQuestionByHomeWorkId(@PathVariable Long id) {
        return homeWorkQuestionService.findHomeWorkQuestionByHomeWorkId(id);
    }


    public HomeWorkQuestion findHomeWorkQuestionById(Long id) {
        return homeWorkQuestionService.findHomeWorkQuestionById(id);
    }

    public int deleteByRef(String ref) {
        return homeWorkQuestionService.deleteByRef(ref);
    }

    @DeleteMapping("/id/{id}")
    public int deleteHomeWorkQuestionById(@PathVariable Long id) {
        return homeWorkQuestionService.deleteHomeWorkQuestionById(id);
    }

}
