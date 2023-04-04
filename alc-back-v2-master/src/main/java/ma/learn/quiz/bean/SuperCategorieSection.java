package ma.learn.quiz.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class SuperCategorieSection implements Serializable{
	 @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     private String code ;
	@Lob
	@Column(length=512)
	private String libelle ;
     @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
     @OneToMany(mappedBy="superCategorieSection")
     private List<CategorieSection> categorieSections;
}
