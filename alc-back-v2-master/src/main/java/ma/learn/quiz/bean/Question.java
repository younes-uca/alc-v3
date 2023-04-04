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
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    @Lob
    @Column(length = 512)
    private String libelle;
    @Lob
    @Column(length = 512)
    private String urlImg;
    @Lob
    @Column(length = 512)
    private String urlVideo;
    private Long numero;
    private double pointReponseJuste = 1;
    private double pointReponsefausse = 0;

    @ManyToOne
    private TypeDeQuestion typeDeQuestion;
    @ManyToOne
    private Quiz quiz;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "question")
    private List<Reponse> reponses;
    @ManyToOne
    private HomeWork homeWork;
}