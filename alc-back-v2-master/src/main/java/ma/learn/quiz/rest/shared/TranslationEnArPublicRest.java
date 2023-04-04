package ma.learn.quiz.rest.shared;

import ma.learn.quiz.bean.SectionItem;
import ma.learn.quiz.service.impl.TranslationEnAr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/public/TranslateEnAr")
public class TranslationEnArPublicRest {
    @Autowired
    private TranslationEnAr translationEnAr;

    @GetMapping("/text/{texttotranslate}")
    public String TranslationResult(@PathVariable String texttotranslate) throws IOException {
        return translationEnAr.TranslationResult(texttotranslate);
    }

    @GetMapping("/text/synonymes/{text}")
    public List<String> synonyme(@PathVariable String text) throws IOException {
        return translationEnAr.synonyme(text);
    }

    @GetMapping("/text/example/{text}")
    public String example(@PathVariable String text) throws IOException {
        return translationEnAr.example(text);
    }

    @GetMapping("/text/explanation/{text}")
    public String explanation(@PathVariable String text) throws IOException {
        return translationEnAr.explanation(text);
    }

    @GetMapping("/text/translationFeatures/{text}")
    public SectionItem translationFeatures(@PathVariable String text) throws IOException {
        return translationEnAr.translationFeatures(text);
    }

    @GetMapping("/text/synonymesinenglish/{text}")
    public List<String> synonymeInEnglish(@PathVariable String text) throws IOException {
        return translationEnAr.synonymeInEnglish(text);
    }


}
