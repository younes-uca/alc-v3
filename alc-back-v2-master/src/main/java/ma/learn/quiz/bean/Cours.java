package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Cours implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    @Column(length = 512)
    private String description;
    @Lob
    @Column(length = 512)
    private String image;
    @Lob
    @Column(length = 512)
    private String libelle;
    private String code;
    private String etatCours;

    @ManyToOne
    private Parcours parcours;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "cours")
    private List<Section> sections;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "cours")
    private List<HomeWork> homeWorkList;

    private int nombreSectionFinalise;
    private int nombreSectionEnCours;
    private int nombreLinkEnCours;
    private int nombreLinkFinalise;

    private int numeroOrder;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cours other = (Cours) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
