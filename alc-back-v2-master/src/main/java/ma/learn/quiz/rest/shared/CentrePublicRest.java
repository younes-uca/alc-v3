package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.Centre;
import ma.learn.quiz.service.impl.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/centre")
public class CentrePublicRest {
    @Autowired
    public CentreService centreService;

    @PostMapping("/")
    public int save(@RequestBody Centre centre) {
        return centreService.save(centre);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return centreService.deleteByRef(ref);
    }

    @GetMapping("/")
    public List<Centre> findAll() {
        return centreService.findAll();
    }

    @GetMapping("/ref/{ref}")
    public Centre findByRef(@PathVariable String ref) {
        return centreService.findByRef(ref);
    }


}
