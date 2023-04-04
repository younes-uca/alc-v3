package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ConfirmationToken {

    /**
     *
     */
    private static final long serialVersionUID = 9031769415590864548L;

    /**
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiresAt;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    @JsonIgnore
    private Etudiant user;


    public ConfirmationToken(String token, LocalDateTime now, LocalDateTime plusDays, Etudiant userRequest) {
        this.token = token;
        this.createdAt = now;
        this.expiresAt = plusDays;
        this.user = userRequest;
    }
}
