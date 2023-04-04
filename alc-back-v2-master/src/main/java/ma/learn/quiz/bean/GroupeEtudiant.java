package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class
GroupeEtudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;
    private String niveau;
    private Long nombrePlace;
    private Long nombrePlacevide;
    private Long nombrePlaceNonVide; // nombre de place non disponible
    @OneToMany(mappedBy = "groupeEtudiant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<GroupeEtudiantDetail> groupeEtudiantDetails;
    @ManyToOne
    private GroupeEtude groupeEtude;
    @ManyToOne
    private Parcours parcours;
    @ManyToOne
    private Prof prof;
}
