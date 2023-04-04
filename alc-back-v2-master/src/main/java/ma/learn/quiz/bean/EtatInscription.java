package ma.learn.quiz.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class EtatInscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;
    private String ref;


    @Override
    public String toString() {
        return "EtatInscription{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", ref='" + ref + '\'' +
                '}';
    }
}
