package ma.learn.quiz.bean;

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
public class ScheduleProf {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private Date startTime;
    private Date endTime;
    private String ref;
    @ManyToOne
    private GroupeEtudiant groupeEtudiant;
    @ManyToOne
    private Prof prof;
    @ManyToOne
    private Cours cours;
    private String grpName;
    private String profName;
    private Long profsId;
}
