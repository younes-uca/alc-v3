package ma.learn.quiz.service.Util;


import java.util.Random;

public class UtilString {
    public static boolean isEmpty(String value) {
        return (value == null || value.isEmpty());

    }


    public static boolean isnotEmpty(String value) {
        return !isEmpty(value);

    }

    public static String generateStringUppercaseAndLowercase(int len) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    public static String generateStringNumber(int len) {
        String chars = "0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

}
