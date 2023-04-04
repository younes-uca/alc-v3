package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.Faq;
import ma.learn.quiz.service.impl.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public/faq")
public class FaqPublicRest {

    @Autowired
    private FaqService faqService;

    @GetMapping("/id/{id}")
    public Optional<Faq> findById(@PathVariable Long id) {
        return faqService.findById(id);
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id) {
        faqService.deleteById(id);
    }

    @GetMapping("/faqType/id/{id}")
    public List<Faq> findByFaqTypeId(@PathVariable Long id) {
        return faqService.findByFaqTypeId(id);
    }

    @DeleteMapping("/faqType/id/{id}")
    public int deleteByFaqTypeId(@PathVariable Long id) {
        return faqService.deleteByFaqTypeId(id);
    }

    @PostMapping("/")
    public int save(@RequestBody Faq faq) {
        return faqService.save(faq);
    }

    @GetMapping("/")
    public List<Faq> findAll() {
        return faqService.findAll();
    }


}
