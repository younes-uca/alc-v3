package ma.learn.quiz.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    @Column(length = 512)
    private String word;
    @Lob
    @Column(length = 512)
    private String definition;
    @ManyToOne
    private Etudiant etudiant;

}

