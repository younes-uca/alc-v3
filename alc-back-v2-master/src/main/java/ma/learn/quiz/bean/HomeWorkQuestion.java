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
public class HomeWorkQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    @Lob
    @Column(length = 512)
    private String libelle;
    private int numero;
    private double pointReponseJuste;
    private double pointReponsefausse;
    @ManyToOne
    private TypeDeQuestion typeDeQuestion;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "homeWorkQuestion", cascade = CascadeType.REMOVE)
    private List<HoweWorkQSTReponse> reponses;

    @ManyToOne
    private HomeWork homeWork;

}
