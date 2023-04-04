package ma.learn.quiz.rest.admin;


import ma.learn.quiz.bean.TypeHomeWork;
import ma.learn.quiz.service.impl.TypeHomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/typeHomeWork")
public class TypeHomeWorkAdminRest {


    @GetMapping("/id/{id}")
    public Optional<TypeHomeWork> findById(@PathVariable Long id) {
        return typeHomeWorkService.findById(id);
    }

    @Transactional
    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id) {
        typeHomeWorkService.deleteById(id);
    }

    @GetMapping("/")
    public List<TypeHomeWork> findAll() {
        return typeHomeWorkService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody TypeHomeWork typeHomeWork) {
        return typeHomeWorkService.save(typeHomeWork);
    }

    @Autowired
    private TypeHomeWorkService typeHomeWorkService;
}
