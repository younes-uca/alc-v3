package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class QuizEtudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Etudiant etudiant;
    @ManyToOne
    private Quiz quiz;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "quizEtudiant")
    private List<ReponseEtudiant> reponseEtudiant;
    private Double note;
    private String resultat;
    private String ref;
    private Long questionCurrent;

    @Override
    public String toString() {
        return "QuizEtudiant{" +
                "id=" + id +
                ", etudiant=" + etudiant +
                ", quiz=" + quiz +
                ", reponseEtudiant=" + reponseEtudiant +
                ", note=" + note +
                ", resultat='" + resultat + '\'' +
                ", ref='" + ref + '\'' +
                ", questionCurrent=" + questionCurrent +
                '}';
    }
}
