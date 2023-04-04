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
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String ref;
    @Lob
    @Column(length = 512)
    private String libelle;
    private String image;
    @Lob
    @Column(length = 512)
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateDebut;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateFin;
    private int numeroOrdre;
    private String destinataire;
}
