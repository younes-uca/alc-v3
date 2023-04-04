package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class ReclamationEtudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dateReclamation;
    @Lob
    @Column(length = 512)
    private String message;
    private String setFrom;
    private String img;
    private Boolean traite;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dateTraitement;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dateReponse;
    private boolean postView;
    private String objetReclamationEtudiant;
    private String commentaireTraiteur;
    @ManyToOne
    private User user;
    @ManyToOne
    @JoinColumn(nullable = true)
    @Nullable
    private TypeReclamationEtudiant typeReclamationEtudiant;
}
