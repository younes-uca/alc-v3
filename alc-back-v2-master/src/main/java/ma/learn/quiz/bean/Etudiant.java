package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Etudiant extends User {
    private String ref;
    private String teacherLocality; //  native || non-native
    private String groupOption;
    @ManyToOne
    private Parcours parcours;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "etudiant")
    private List<QuizEtudiant> quizEtudiant;

    @ManyToOne
    private GroupeEtude groupeEtude;
    @OneToMany(mappedBy = "etudiant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<GroupeEtudiantDetail> groupeEtudiantDetails;
    @ManyToOne
    private PackStudent packStudent;
    @ManyToOne
    private StatutSocial statutSocial;
    @ManyToOne
    private InteretEtudiant interetEtudiant;
    @ManyToOne
    private NiveauEtude niveauEtude;
    @ManyToOne
    private Skill skill;
    @ManyToOne
    private Fonction fonction;

    private String langue;

}