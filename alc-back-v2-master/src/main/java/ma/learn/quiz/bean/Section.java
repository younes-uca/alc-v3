package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Section implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    @Lob
    @Column(length = 512)
    private String libelle;
    @Lob
    @Column(length = 512)
    private String urlImage;
    @Lob
    @Column(length = 512)
    private String urlImage2;
    @Lob
    @Column(length = 512)
    private String urlImage3;
    @Lob
    @Column(length = 512)
    private String urlVideo;
    @Lob
    @Column(length = 512)
    private String contenu;
    @Lob
    @Column(length = 512)
    private String questions;
    @Lob
    @Column(length = 512)
    private String indicationProf;
    private Integer numeroOrder;
    @ManyToOne
    private CategorieSection categorieSection;
    @ManyToOne
    private Cours cours;

    private int url;
    private int content;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "section", cascade = CascadeType.REMOVE)
    private List<SectionItem> sectionItems;

    @ManyToOne
    private SessionCours sessionCours;

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", urlImage2='" + urlImage2 + '\'' +
                ", urlImage3='" + urlImage3 + '\'' +
                ", urlVideo='" + urlVideo + '\'' +
                ", contenu='" + contenu + '\'' +
                ", questions='" + questions + '\'' +
                ", indicationProf='" + indicationProf + '\'' +
                ", numeroOrder=" + numeroOrder +
                ", categorieSection=" + categorieSection +
                ", cours=" + cours +
                ", url=" + url +
                ", content=" + content +
                ", sectionItems=" + sectionItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return url == section.url && content == section.content && Objects.equals(id, section.id) && Objects.equals(code, section.code) && Objects.equals(libelle, section.libelle) && Objects.equals(urlImage, section.urlImage) && Objects.equals(urlImage2, section.urlImage2) && Objects.equals(urlImage3, section.urlImage3) && Objects.equals(urlVideo, section.urlVideo) && Objects.equals(contenu, section.contenu) && Objects.equals(questions, section.questions) && Objects.equals(indicationProf, section.indicationProf) && Objects.equals(numeroOrder, section.numeroOrder) && Objects.equals(categorieSection, section.categorieSection) && Objects.equals(cours, section.cours) && Objects.equals(sectionItems, section.sectionItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, libelle, urlImage, urlImage2, urlImage3, urlVideo, contenu, questions, indicationProf, numeroOrder, categorieSection, cours, url, content, sectionItems);
    }
}
