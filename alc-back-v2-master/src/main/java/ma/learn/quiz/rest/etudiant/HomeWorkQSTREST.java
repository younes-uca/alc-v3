package ma.learn.quiz.rest.etudiant;

import ma.learn.quiz.bean.HomeWorkQuestion;
import ma.learn.quiz.service.impl.HomeWorkQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("etudiant/homeWorkQST")
public class HomeWorkQSTREST {

    @Autowired
    private HomeWorkQuestionService homeWorkQuestionService;

    @GetMapping("/homework/{id}")
    public List<HomeWorkQuestion> findHomeWorkQuestionByHomeWorkId(@PathVariable Long id) {
        return homeWorkQuestionService.findHomeWorkQuestionByHomeWorkId(id);
    }


    public int deleteByRef(String ref) {
        return homeWorkQuestionService.deleteByRef(ref);
    }

    @DeleteMapping("/id/{id}")
    public int deleteHomeWorkQuestionById(@PathVariable Long id) {
        return homeWorkQuestionService.deleteHomeWorkQuestionById(id);
    }

}
