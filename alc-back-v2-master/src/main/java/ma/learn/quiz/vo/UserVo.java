package ma.learn.quiz.vo;

import lombok.Data;

@Data
public class UserVo {
    private long id;
    private String nom;
    private String username;

    public UserVo() {
    }

    public UserVo(long id, String nom, String username) {
        this.id = id;
        this.nom = nom;
        this.username = username;
    }
}
