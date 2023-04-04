package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.TypeTeacher;
import ma.learn.quiz.service.impl.TypeTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prof/typeTeacher")
public class TypeTeacherRest {
    @Autowired
    private TypeTeacherService typeTeacherService;

    @GetMapping("/")
    public List<TypeTeacher> findAll() {
        return typeTeacherService.findAll();
    }
}
