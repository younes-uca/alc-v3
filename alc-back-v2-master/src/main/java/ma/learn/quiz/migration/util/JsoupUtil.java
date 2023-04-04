/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.learn.quiz.migration.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * @author MoulaYounes
 */
public class JsoupUtil {

    public static String getElementContentByClass(File in, String selector) throws IOException {
        Document document = Jsoup.parse(in, null);
        Element first = document.select(selector).first();
        return first == null ? "" : first.children().outerHtml();
    }

    public static String getElementContentLesson(File in, String selector) throws IOException {
        Document document = Jsoup.parse(in, null);
        Elements first = document.select(selector + ">p");
        String lessonp = "";
        String lessonul = "";
        Elements first1 = document.select(selector + ">ul>li");
        for (Element element : first) {
            lessonp += element.text() + "\n";
        }
        for (Element element : first1) {
            lessonul += element.text() + "\n";
        }
        return lessonp + lessonul;
    }

    public static String getElementContent(File in, String selector) throws IOException {
        Document document = Jsoup.parse(in, null);
        Elements first = document.select(selector);
        return first.text();
    }

    public static Elements getElements(File in, String selector) throws Exception {
        Document document = Jsoup.parse(in, null);
        return document.select(selector);
    }



    public static String getImageSrc(File in) throws IOException {
        Element image = getImage(in);
        if (image == null) {
            return "";
        } else {
            String imageSrc = image.attr("src");
            if (imageSrc.startsWith(".")) {
                return imageSrc.substring(2).replace("/", "\\");
            } else if (imageSrc.startsWith("https")) {
                return imageSrc;
            } else {
                return "";
            }
        }
    }

    public static Element getImage(File in) throws IOException {
        // File in = new File(path);
        Document document = Jsoup.parse(in, null);

        return document.select("img[src~=(?i)\\.(png|jpe?g|gif)]").first();

    }

}
