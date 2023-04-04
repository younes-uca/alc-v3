package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class HomeWorkEtudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Etudiant etudiant;
    @ManyToOne()
    private HomeWork homeWork;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "homeWorkEtudiant", cascade = CascadeType.REMOVE)
    private List<ReponseEtudiantHomeWork> reponseEtudiantHomeWork;
    private Double note;
    private String resultat;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

}
