package ma.learn.quiz.rest.admin;


import ma.learn.quiz.bean.FreeTrialformule;
import ma.learn.quiz.dao.FreeTrialformuleDao;
import ma.learn.quiz.service.impl.FreeTrialFormuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/freeTrial")
public class FreeTrialFormuleRest {

    @Autowired
    private FreeTrialFormuleService freeTrialFormuleService;
    @Autowired
    private FreeTrialformuleDao freeTrialformuleDao;


    @GetMapping("/code/{code}")
    public FreeTrialformule findByCode(@PathVariable String code) {
        return freeTrialFormuleService.findByCode(code);
    }

    @GetMapping("/")
    public List<FreeTrialformule> findAll(){
        return this.freeTrialformuleDao.findAll();
    }

    @GetMapping("/inscription/id/{id}")
    public FreeTrialformule findByInscriptionId(@PathVariable Long id) {
        return freeTrialFormuleService.findByInscriptionId(id);
    }

    @PostMapping("/")
    public int save(@RequestBody FreeTrialformule freeTrialformule) {
        return freeTrialFormuleService.save(freeTrialformule);
    }

    @PostMapping("/criteria")
    public List<FreeTrialformule> findByCriteria(@RequestBody FreeTrialformule freeTrialformuleVo) {
        return freeTrialFormuleService.findByCriteria(freeTrialformuleVo);
    }
}
