package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class HomeWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    @Column(length = 512)
    private String libelle;
    @Lob
    @Column(length = 512)
    private String urlImage;
    @Lob
    @Column(length = 512)
    private String urlVideo;
    @OneToMany(mappedBy = "homeWork", cascade = CascadeType.REMOVE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<HomeWorkQuestion> questions;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "homeWork", cascade = CascadeType.REMOVE)
    private List<HomeWorkEtudiant> homeWorkEtudiant;

    @ManyToOne
    private Cours cours;

    @ManyToOne
    private TypeHomeWork typeHomeWork;

}
