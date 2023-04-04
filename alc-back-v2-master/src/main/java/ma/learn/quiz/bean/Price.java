package ma.learn.quiz.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    private static final long serialVersionUID = 9031769415590864548L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private double price;
    private double oldPrice;
    private String lib;
    private double nreCourse;
    private double nreHours;
    private double nreMonth;
    private boolean forGroup;
}
