package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Prof extends User {
    private String ref;
    @Column(length = 512)
    private String about;
    @OneToOne
    @Nullable
    Parcours levelMin;
    @OneToOne
    @Nullable
    Parcours levelMax;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "prof")
    List<TrancheHoraireProf> trancheHoraireProfList = new ArrayList<>();
    @ManyToOne
    private CategorieProf categorieProf;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "responsable")
    private List<ClassRoom> classRooms;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "prof")
    private List<RecommendTeacher> recommendTeacher;
    @ManyToOne
    private TypeTeacher typeTeacher;
}
