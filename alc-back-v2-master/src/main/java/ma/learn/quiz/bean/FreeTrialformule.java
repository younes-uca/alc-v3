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
public class FreeTrialformule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    @OneToOne
    private Inscription inscription;
    private String dayspeerweek;
    private String timeperday;
    private String teacherGenderoption;
    private String teacherAgeRange;
    private String teacherPersonnality;
    private boolean status;
    @Temporal(TemporalType.DATE)
    private Date dateConfirmation;

}
