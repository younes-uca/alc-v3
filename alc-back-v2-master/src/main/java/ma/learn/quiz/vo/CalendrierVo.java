package ma.learn.quiz.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
public class CalendrierVo {

    public void setDaysOfWeek(int[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    private Long id;
    private String title;
    private String titleProf;
    private String ref;
    private String color;
    private String startTime;
    private String endTime;
    private Date startRecur;
    private Date endRecur;
    private int daysOfWeek[];

}
