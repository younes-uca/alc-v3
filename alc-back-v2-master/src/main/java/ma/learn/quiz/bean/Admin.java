package ma.learn.quiz.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User {
}
