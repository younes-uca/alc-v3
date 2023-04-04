package ma.learn.quiz.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SectionItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
	@Column(length=512)
    private String imageUrl;
    @Lob
	@Column(length=512)
    private String response;

    private String transcription;
    @Lob
	@Column(length=512)
    private String translation;
    @Lob
	@Column(length=512)
    private String explanation;
    @Lob
	@Column(length=512)
    private String example;
    @ElementCollection
    private List<String> synonyms;
    @ManyToOne()
    private Section section;


}
