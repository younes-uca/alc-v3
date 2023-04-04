package ma.learn.quiz.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailComponent {

    private String username;

    private String link;

    private String subject;

    private String content;

    private String password;

    private String to;

    private String from;
}
