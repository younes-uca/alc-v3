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
public class ProfReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Etudiant etudiant;
    @ManyToOne
    private Prof prof;
    @ManyToOne
    private Cours cours;
    private int review;
    @Lob
    @Column(length = 512)
    private String comment;
    private Date dateReview;

}
