package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.SectionItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TranslationEnAr {


    public String TranslationResult(String texttotranslate) throws IOException {
        Document doc = Jsoup.connect("https://translate.google.com/m?sl=en&tl=ar&hl=en&q=" + texttotranslate).get();
        String result = doc.select("div.result-container").text();
        System.out.println(result);
        return result;
    }

    public String translationArToFr(String texttotranslate) throws IOException {
        Document doc = Jsoup.connect("https://translate.google.com/m?sl=ar&tl=fr&hl=ar&q=" + texttotranslate).get();
        String result = doc.select("div.result-container").text();
        System.out.println(result);
        return result;
    }

    public String TranslationResultEnFr(String texttotranslate) throws IOException {
        Document doc = Jsoup.connect("https://translate.google.com/m?sl=en&tl=fr&hl=en&q=" + texttotranslate).get();
        String result = doc.select("div.result-container").text();
        return result;
    }

    public List<String> synonyme(String word) throws IOException {
        Document doc = Jsoup.connect("https://www.thesaurus.com/browse/" + word).get();
        Elements elements = doc.select("ul.e1ccqdb60");
        List<String> listesynonymes = new ArrayList<>();
        int i = 0;
        for (Element e : elements) {
            Elements items = e.select("li");
            for (Element item : items) {
                i++;
                listesynonymes.add(item.text());
                if (i == 10) break;
            }
            break;
        }
        return listesynonymes;
    }

    public String example(String word) {
        String example = null;
        try {
            Document doc = Jsoup.connect("https://sentence.yourdictionary.com/" + word).get();
            Elements elements = doc.select("ul.sentences-list");
            for (Element e : elements) {
                Elements items = e.select("li.sentences-list-item");
                for (Element item : items) {
                    example = item.text();
                    System.out.println("============example ==============");
                    System.out.println(example);
                    break;
                }
            }

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
            example = "WARNING : You should only set one word to get it in example";
        }

        return example;
    }

    public String explanation(String texttoexplain) throws IOException {
        String textwithoutspace = texttoexplain.replace(" ", "-");
        Document doc = Jsoup.connect("https://www.oxfordlearnersdictionaries.com/definition/english/" + textwithoutspace + "?q=" + textwithoutspace).get();
        System.out.println(doc.select("span.def").first().text());
        return doc.select("span.def").first().text();
    }


    public String getImg(String texttoexplain) throws IOException {
        String textwithoutspace = texttoexplain.replace(" ", "-");
        Document doc = Jsoup.connect("https://www.oxfordlearnersdictionaries.com/definition/english/" + textwithoutspace + "?q=" + textwithoutspace).get();
        String src = doc.select("a.topic").attr("href");
        System.out.println(src);
        return src;
    }

    public SectionItem translationFeatures(String textTaped) throws IOException {
        SectionItem sectionItem = new SectionItem();
        sectionItem.setTranslation(this.TranslationResult(textTaped));
        this.getImg(textTaped);
        sectionItem.setExample(example(textTaped));
        sectionItem.setExplanation(explanation(textTaped));
        sectionItem.setSynonyms(synonyme(textTaped));
        return sectionItem;
    }

    public List<String> synonymeInEnglish(String word) throws IOException {
        Document doc = Jsoup.connect("https://www.oxfordlearnersdictionaries.com/definition/english/" + word + "?q=" + word).get();
        Elements elements = doc.select("li.li");
        List<String> listesynonymes = new ArrayList<>();
        int i = 0;
        for (Element e : elements) {
            if (!e.text().startsWith(word)) {
                i++;
                listesynonymes.add(e.text());
            }
            if (i >= 2)
                break;
        }
        return listesynonymes;
    }


}
