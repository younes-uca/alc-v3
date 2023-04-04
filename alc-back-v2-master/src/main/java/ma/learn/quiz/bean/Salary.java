package ma.learn.quiz.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Prof prof;
    private int annee;
    private int mois;
    private String code;
    private BigDecimal nbrSessionMensuel;
    private boolean payer;
    private BigDecimal totalPayment;
    private BigDecimal totalBonusClassAverage;
    private BigDecimal totalBonusWorkload;
}
