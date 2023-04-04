package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.ClassRoom;
import ma.learn.quiz.bean.Quiz;
import ma.learn.quiz.bean.QuizClassRoom;
import ma.learn.quiz.dao.QuizClassRoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class QuizClassRoomService {
	@Autowired
	private QuizClassRoomDao quizClassRoomdao;
	@Autowired
	private ClassRoomService classRoomService;
	@Autowired
	private QuizService quizService;

	public int save(QuizClassRoom quizClassRoom) {
		Optional<ClassRoom> classRoom = classRoomService.findById(quizClassRoom.getClassRoom().getId());
		Quiz quiz = quizService.findByRef(quizClassRoom.getQuiz().getRef());
		if(classRoom == null)
		{
			return -1;
		}
		else if(quiz == null)
		{
			return -2;
		}
		else
		{
			quizClassRoomdao.save(quizClassRoom);
			return -1;
		}
	}

	public List<QuizClassRoom> findByClassRoomId(Long id) {
		return quizClassRoomdao.findByClassRoomId(id);
	}

	@Transactional
	public int deleteByClassRoomId(Long id) {
		return quizClassRoomdao.deleteByClassRoomId(id);
	}

	public List<QuizClassRoom> findAll() {
		return quizClassRoomdao.findAll();
	}
	
	
}
