package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.ClassRoom;
import ma.learn.quiz.service.impl.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prof/classRoom")
public class ClassRoomProfRest {
    @Autowired
    private ClassRoomService classRoomService;

    @PostMapping
    public int save(@RequestBody ClassRoom classRoom) {
        return classRoomService.save(classRoom);
    }

    @GetMapping("/id/{id}")
    public Optional<ClassRoom> findById(@PathVariable Long id) {
        return classRoomService.findById(id);
    }

    @DeleteMapping("/Prof/id/{id}")
    public int deleteByResponsableId(@PathVariable Long id) {
        return classRoomService.deleteByResponsableId(id);
    }

    @GetMapping("/Prof/id/{id}")
    public List<ClassRoom> findByResponsableId(@PathVariable Long id) {
        return classRoomService.findByResponsableId(id);
    }

    @GetMapping("/")
    public List<ClassRoom> findAll() {
        return classRoomService.findAll();
    }

}
