/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.learn.quiz.migration.util;

//import data.base.migration.DataBaseMigration;


import ma.learn.quiz.migration.DataBaseMigration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author MoulaYounes
 */
public class FileUtil {


    public static List<File> findHtmlFiles(String directoryName) {
        File directory = new File(directoryName);
        List<File> result = new ArrayList();
        // Get all files from a directory.
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    result.add(file);
                }
            }
        }
        return result;
    }

    public static String getFileName(String fileName) {
        String[] fileparts = fileName.split("\\.");
        return fileparts[0]; //Get first part
    }

    public static File mkdire(String directoryName, String images, boolean deleteAsInitialisation) {
        File file = new File(directoryName + "\\" + images);

        if (deleteAsInitialisation) {
            file.delete();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static String getExtension(String filename) {
        return Optional.of(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .orElse(filename);
    }

    public static String fileNameWithOutExt(String fileName) {
        String[] split = null;
        if (fileName.startsWith("https")) {
            split = fileName.split("/");
        } else {
            split = fileName.split("\\\\");
        }
        String name = split[split.length - 1];
        return Optional.of(name.lastIndexOf(".")).filter(i -> i >= 0)
                .map(i -> name.substring(0, i)).orElse(name);
    }

    public static String getBaseName(String filename) {
        return removeExtension(getName(filename));
    }

    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        } else {
            int lastUnixPos = filename.lastIndexOf(47);
            int lastWindowsPos = filename.lastIndexOf(92);
            return Math.max(lastUnixPos, lastWindowsPos);
        }
    }

    public static String getName(String filename) {
        if (filename == null) {
            return null;
        } else {
            int index = indexOfLastSeparator(filename);
            return filename.substring(index + 1);
        }
    }

    public static String removeExtension(String filename) {
        if (filename == null) {
            return null;
        } else {
            int index = indexOfExtension(filename);
            return index == -1 ? filename : filename.substring(0, index);
        }
    }

    public static int indexOfExtension(String filename) {
        if (filename == null) {
            return -1;
        } else {
            int extensionPos = filename.lastIndexOf(46);
            int lastSeparator = indexOfLastSeparator(filename);
            return lastSeparator > extensionPos ? -1 : extensionPos;
        }
    }

    public static int compare(File f1, File f2) {

        String fileName1 = FileUtil.getFileName(f1.getName());
        String fileName2 = FileUtil.getFileName(f2.getName());
        System.out.println("=========START========");
        System.out.println(fileName1);
        System.out.println(f1.getName());
        System.out.println(f1.getAbsolutePath());
        System.out.println("=======FINISH==========");

        if (!fileName1.contains("-") && !fileName2.contains("-")) {
            return (new Integer(fileName1).compareTo(new Integer(fileName2)));
        } else if (!fileName1.contains("-") && fileName2.contains("-")) {
            String[] split2 = fileName2.split("-");
            return (new Integer(fileName1).compareTo(new Integer(split2[0])));
        } else if (fileName1.contains("-") && !fileName2.contains("-")) {
            String[] split1 = fileName1.split("-");
            return (new Integer(split1[0]).compareTo(new Integer(fileName2)));
        } else {
            String[] split1 = fileName1.split("-");
            String[] split2 = fileName2.split("-");
            if (split1[0].equals(split2[0])) {
                return (new Integer(split1[1]).compareTo(new Integer(split2[1])));
            } else {
                return (new Integer(split1[0]).compareTo(new Integer(split2[0])));
            }
        }
    }

    public static void copyFile(String sourcePath, String destPath) {
        if (sourcePath == null || sourcePath.equals("") || destPath == null || destPath.equals("")) {
            return;
        }
        File dest = new File(destPath);
        File source = new File(sourcePath);
        if (!source.exists()) {
            System.out.println("//////////// source.getName() not exist= " + source.getName());
        }
        try {
            Files.copy(source.toPath(), dest.toPath());
        } catch (IOException ex) {
            Logger.getLogger(DataBaseMigration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
