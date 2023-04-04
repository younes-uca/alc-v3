package ma.learn.quiz.util;

import ma.learn.quiz.bean.Section;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SynchronizationHandler {

    private Map<Long, Section> currentSectionsMap = new HashMap<>();

    public int saveCurrentSectionByProfId(Long id, Section currentSection) {
        this.currentSectionsMap.put(id, currentSection);
        return 1;
    }

    public int updateCurrentSectionByProfId(Long id, Section updatedSection) {
        this.currentSectionsMap.remove(id);
        this.saveCurrentSectionByProfId(id, updatedSection);
        System.out.println("curent section updated for  :  " + this.currentSectionsMap.get(id).getLibelle());
        return 1;
    }

    public Section getcurrentSectionByProf(Long id) {
        System.out.println("curent section found  :  " + this.currentSectionsMap.get(id).getLibelle());
        return this.currentSectionsMap.get(id);
    }

    public int removeSessionWhenFinish(Long id) {
        this.currentSectionsMap.remove(id);
        return 1;
    }
}
