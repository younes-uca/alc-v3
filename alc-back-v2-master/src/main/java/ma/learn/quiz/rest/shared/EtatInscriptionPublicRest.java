package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.EtatInscription;
import ma.learn.quiz.service.impl.EtatInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/public/etatInscription")
public class EtatInscriptionPublicRest {
    @Autowired
    public EtatInscriptionService etatInscriptionService;

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return etatInscriptionService.deleteByRef(ref);
    }

    @GetMapping("/")
    public List<EtatInscription> findAll() {
        return etatInscriptionService.findAll();
    }

    @GetMapping("/ref/{ref}")
    public EtatInscription findByRef(@PathVariable String ref) {
        return etatInscriptionService.findByRef(ref);
    }

    @PostMapping("/")
    public int save(@RequestBody EtatInscription etatInscription) {
        return etatInscriptionService.save(etatInscription);
    }
}
