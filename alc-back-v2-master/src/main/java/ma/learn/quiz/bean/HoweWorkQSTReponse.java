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
public class HoweWorkQSTReponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    @Lob
    @Column(length = 512)
    private String lib;
    private String etatReponse;
    private int numero;
    @ManyToOne
    private HomeWorkQuestion homeWorkQuestion;

}
