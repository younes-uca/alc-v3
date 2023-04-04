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
public class FaqEtudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    @Column(length = 512)
    private String libelle;
    @Lob
    @Column(length = 512)
    private String description;
    @ManyToOne
    private Etudiant etudiant;
    @ManyToOne
    private FaqType faqType;
    @ManyToOne
    private Admin admin;
}
