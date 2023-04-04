package ma.learn.quiz.vo;

import lombok.Data;
import lombok.Getter;
import ma.learn.quiz.bean.Etudiant;

import java.time.LocalDate;

@Data
@Getter
public class UserCodeValidate {
    private int randomNumber;
    private Etudiant student;
    private LocalDate date;

    public UserCodeValidate(int randomNumber, Etudiant student) {
        this.randomNumber = randomNumber;
        this.student = student;
        date = LocalDate.now().plusDays(1); // after 24h
    }

    @Override
    public String toString() {
        return "UserCodeValidate{" +
                "randomNumber=" + randomNumber +
                ", student=" + student +
                ", date=" + date +
                '}';
    }
}
