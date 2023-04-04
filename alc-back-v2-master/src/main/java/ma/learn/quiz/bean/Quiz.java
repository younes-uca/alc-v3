package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    @Lob
    @Column(length = 512)
    private String lib;
    // @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss")
    private Date dateDebut;
    // @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss")
    private Date dateFin;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "quiz")
    private List<QuizEtudiant> quizEtudiant;
    @OneToOne
    private Section section;
    private Long numero;
    private Long seuilReussite;
}
