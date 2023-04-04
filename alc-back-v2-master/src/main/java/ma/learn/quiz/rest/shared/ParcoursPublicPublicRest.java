package ma.learn.quiz.rest.shared;


import ma.learn.quiz.bean.Parcours;
import ma.learn.quiz.service.impl.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/parcours")

public class ParcoursPublicPublicRest {

    @Autowired
    public ParcoursService parcoursService;

    @GetMapping("/")
    public List<Parcours> findAll() {
        return parcoursService.findAll();
    }


    @GetMapping("/code/{code}")
    public List<Parcours> findByCode(@PathVariable String code) {
        return parcoursService.findByCode(code);
    }


    @GetMapping("/id/{id}")
    public Parcours findById(@PathVariable long id) throws Exception {
        return parcoursService.findById(id);
    }
}
