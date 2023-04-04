package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.News;
import ma.learn.quiz.service.impl.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/news")
public class NewsPublicRest {

    @Autowired
    private NewsService newsService;

    @GetMapping("/critere/destinataire/{destinataire}")
    public List<News> findByCritere(@PathVariable String destinataire) {
        return newsService.findByCritere(destinataire);
    }

    @PutMapping("/")
    public void update(@RequestBody News news) {
        newsService.update(news);
    }

    @GetMapping("/ref/{ref}")
    public News findByRef(@PathVariable String ref) {
        return newsService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return newsService.deleteByRef(ref);
    }

    @PostMapping("/")
    public int save(@RequestBody News news) {
        return newsService.save(news);
    }

    @GetMapping("/")
    public List<News> findAll() {
        return newsService.findAll();
    }
}
