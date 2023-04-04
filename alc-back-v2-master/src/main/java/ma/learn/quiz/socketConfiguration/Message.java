package ma.learn.quiz.socketConfiguration;

import lombok.Data;
import ma.learn.quiz.bean.Prof;
import ma.learn.quiz.bean.Reponse;
import ma.learn.quiz.bean.User;

import java.util.Date;

@Data
public class Message {

    private String key;
    private String type;
    private String ev;
    private String user;
    private String message;
    private Date dateSent;
    private boolean isStudent;
    Prof prof = new Prof();
    User student = null;
    Reponse quizReponse = null;
}
