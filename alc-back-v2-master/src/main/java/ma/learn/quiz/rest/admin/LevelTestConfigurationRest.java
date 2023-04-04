package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.LevelTestConfiguration;
import ma.learn.quiz.service.impl.LevelTestConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/level-test-configuration")

public class LevelTestConfigurationRest {
    @Autowired
    private LevelTestConfigurationService levelTestConfigurationService;


    @GetMapping("/")
    public List<LevelTestConfiguration> findAll() {
        return levelTestConfigurationService.findAll();
    }

    @PostMapping("/")
    public LevelTestConfiguration save(LevelTestConfiguration entity) {
        return levelTestConfigurationService.save(entity);
    }

}
