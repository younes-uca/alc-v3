package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.ProfReview;
import ma.learn.quiz.service.impl.ProfReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prof/profReview")
public class ProfReviewProfRest {
    @GetMapping("/review/{review}")
    public ProfReview findByReview(@PathVariable int review) {
        return profReviewService.findByReview(review);
    }

    @DeleteMapping("/review/{review}")
    public int deleteByReview(@PathVariable int review) {
        return profReviewService.deleteByReview(review);
    }

    @GetMapping("/etudiant/id/{id}/cours/id/{ids}/prof/id/{idd}")
    public ProfReview findByEtudiantIdAndCoursIdAndProfId(@PathVariable long id, @PathVariable long ids, @PathVariable long idd) {
        return profReviewService.findByEtudiantIdAndCoursIdAndProfId(id, ids, idd);
    }

    @PostMapping("/")
    public int save(@RequestBody ProfReview profReview) {
        return profReviewService.save(profReview);
    }

    @Autowired
    public ProfReviewService profReviewService;

}
