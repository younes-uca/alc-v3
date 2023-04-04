package ma.learn.quiz.bean;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class RecommendTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int nombrevote;
    private String nom;
    private String ref;
    private String prenom;
    private String commentaire;
    private String telephone;
    private String login;

    private Date dateRecommamdation;
    @ManyToOne
    private Prof prof;
}
