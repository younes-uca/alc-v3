package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class ReclamationProf {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dateReclamation;
    @Lob
    @Column(length = 512)
    private String message;
    private Boolean traite;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dateTraitement;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dateReponse;
    private boolean postView;
    private String commentaireTraiteur;
    private String objetReclamationProf;
    @ManyToOne
    private Admin admin;
    @ManyToOne
    private Prof prof;

    @ManyToOne
    private TypeReclamationProf typeReclamationProf;
}
