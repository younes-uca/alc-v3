package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class CategorieProf {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String level;
    private BigDecimal lessonRate;
    private String code;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "categorieProf", cascade = CascadeType.ALL)
    private List<Prof> profs;
}
