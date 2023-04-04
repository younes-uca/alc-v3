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
public class PackStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int nombreCours;
    private boolean forGroupe;
    private String code;
    private String libelle;
    @Lob
    @Column(length = 512)
    private String description;
    @Lob
    @Column(length = 512)
    private String preRequisites;
    @Lob
    @Column(length = 512)
    private String whyTakeThisCourse;
    @Lob
    @Column(length = 512)
    private String expectations;
    @ManyToOne
    private Parcours level;
    private String imgUrl;
    @ManyToOne
    private Price price;
    private int totalStudents;
    private String rating;
    private String oldPrice;
}
