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
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int numeroInscription;
    @ManyToOne
    private Etudiant etudiant;
    @ManyToOne
    private EtatInscription etatInscription;
    @ManyToOne
    private Parcours parcours;
    @ManyToOne
    private GroupeEtude groupeEtude;
    @ManyToOne
    private StatutSocial statutSocial;
    @ManyToOne
    private InteretEtudiant interetEtudiant;
    @ManyToOne
    private NiveauEtude niveauEtude;
    @ManyToOne
    private Fonction fonction;

    @ManyToOne
    private Quiz quizNiveau;
    private double noteQuizNiveau;
    private boolean quizFinished;
    private String dateRegistration;
    @Temporal(TemporalType.DATE)
    private Date datedebutinscription;
    @Temporal(TemporalType.DATE)
    private Date datefininscription;
    @ManyToOne
    private PackStudent packStudent;
    @ManyToOne
    private Skill skill;
    private String skype;

    @Override
    public String toString() {
        return "Inscription{" +
                "id=" + id +
                ", numeroInscription=" + numeroInscription +
                ", etudiant=" + etudiant.getUsername() +
                ", etatInscription=" + etatInscription +
                ", parcours=" + parcours.getLibelle() +
                ", groupeEtude=" + groupeEtude +
                ", statutSocial=" + statutSocial +
                ", interetEtudiant=" + interetEtudiant +
                ", niveauEtude=" + niveauEtude +
                ", fonction=" + fonction +
                ", quizNiveau=" + quizNiveau +
                ", noteQuizNiveau=" + noteQuizNiveau +
                ", quizFinished=" + quizFinished +
                ", dateRegistration='" + dateRegistration + '\'' +
                ", datedebutinscription=" + datedebutinscription +
                ", datefininscription=" + datefininscription +
                ", packStudent=" + packStudent.getId() +
                ", skill=" + skill +
                ", skype='" + skype + '\'' +
                '}';
    }
}



