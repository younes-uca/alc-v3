/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.learn.quiz.migration.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
//import sun.net.www.content.image.png;

/**
 * @author MoulaYounes
 */
public class DownloaderUtil {

    public static void exec(String httpLink, String filePathToSaveIn, String fileExtention) {
        BufferedImage image = null;
        try {
            URL url = new URL(httpLink);
//            URL url = new URL("https://cdn-englishdom.gcdn.co/dynamicus/lesson-element/000/053/363/1526281442.35_816x_content.jpg");
            image = ImageIO.read(url);
            ImageIO.write(image, fileExtention, new File(filePathToSaveIn));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
