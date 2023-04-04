package ma.learn.quiz.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.learn.quiz.bean.Prof;

import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
public class SchdeduleVo {
    private Long id;
    private String ref;
    private String title;
    private Date start;
    private Date end;
    private Prof prof;
    private String color;


}
