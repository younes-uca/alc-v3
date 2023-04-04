package ma.learn.quiz.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class ReponseEtudiantHomeWork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private HoweWorkQSTReponse reponse;
    @Lob
    @Column(length = 512)
    private String answer;
    @ManyToOne
    private HomeWorkEtudiant homeWorkEtudiant;
    @ManyToOne
    private HomeWorkQuestion question;
    @Column(length = 10000)
    private String profNote;
    private Double note;
}
