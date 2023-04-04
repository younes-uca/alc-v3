package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class SessionCours implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;

    @ManyToOne
    private Prof prof;
    @OneToOne
    private Cours cours;
    @ManyToOne
    private GroupeEtudiant groupeEtudiant;
    private double duree;
    private double totalheure;
    private double mois;
    private double annee;
    private Date dateFin;
    private Date dateDebut;
    private boolean payer;
    @ManyToOne
    private Salary salary;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "sessionCours", cascade = CascadeType.REMOVE)
    private List<Section> sections;

}
