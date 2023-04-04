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
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long numero;
    private String word;
    @Lob
    @Column(length = 512)
    private String libelle;
    private String result;
    @Lob
    @Column(length = 512)
    private String explication;
    @Lob
    @Column(length = 512)
    private String exemple;
    @Lob
    @Column(length = 512)
    private String image;
    private String ref;
    @OneToOne
    private Section section;
    @ManyToOne
    private VocabularyQuiz vocabularyQuiz;
}
