package ma.learn.quiz.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class ReponseEtudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Reponse reponse;
    @Lob
    @Column(length = 512)
    private String answer;
    private String ref;
    @ManyToOne
    private QuizEtudiant quizEtudiant;
    private Double note;
    @ManyToOne
    private Question question;
}
