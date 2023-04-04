package ma.learn.quiz.rest.prof;

import ma.learn.quiz.bean.QuizClassRoom;
import ma.learn.quiz.service.impl.QuizClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prof/quiz-classRoom")
public class QuizClassRoomProfRest {
    @Autowired
    private QuizClassRoomService quizClassRoomService;

    @PostMapping("/")
    public int save(@RequestBody QuizClassRoom quizClassRoom) {
        return quizClassRoomService.save(quizClassRoom);
    }

    @GetMapping("/id/{id}")
    public List<QuizClassRoom> findByClassRoomId(@PathVariable Long id) {
        return quizClassRoomService.findByClassRoomId(id);
    }

    @DeleteMapping("/id/{id}")
    public int deleteByClassRoomId(@PathVariable Long id) {
        return quizClassRoomService.deleteByClassRoomId(id);
    }

    @GetMapping("/")
    public List<QuizClassRoom> findAll() {
        return quizClassRoomService.findAll();
    }

}
