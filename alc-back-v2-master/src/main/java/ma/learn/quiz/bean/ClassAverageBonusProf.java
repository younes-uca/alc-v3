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
public class ClassAverageBonusProf {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Prof prof;
    @ManyToOne
    private ClassAverageBonus classAverageBonus;
    private int mois;
    private int annee;
    @ManyToOne
    private Salary salary;
}
