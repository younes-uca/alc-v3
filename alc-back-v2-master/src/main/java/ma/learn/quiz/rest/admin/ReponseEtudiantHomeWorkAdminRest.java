package ma.learn.quiz.rest.admin;


import ma.learn.quiz.bean.ReponseEtudiantHomeWork;
import ma.learn.quiz.service.impl.ReponseEtudiantHomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/reponseEtudiantHomeWork")
public class ReponseEtudiantHomeWorkAdminRest {

	@GetMapping("/id/{id}")
	public Optional<ReponseEtudiantHomeWork> findById(@PathVariable Long id) {
		return reponseEtudiantHomeWorkService.findById(id);
	}

	@Transactional
	@DeleteMapping("/id/{id}")
	public void deleteById(@PathVariable Long id) {
		reponseEtudiantHomeWorkService.deleteById(id);
	}


	@GetMapping("/homeWorkEtudiant/id/{id}")
	public List<ReponseEtudiantHomeWork> findByHomeWorkEtudiantId(@PathVariable Long id) {
		return reponseEtudiantHomeWorkService.findByHomeWorkEtudiantId(id);
	}

	@GetMapping("/homeWorkEtudiant/etudiant/id/{id}")
	public List<ReponseEtudiantHomeWork> findByHomeWorkEtudiantEtudiantId(@PathVariable Long id) {
		return reponseEtudiantHomeWorkService.findByHomeWorkEtudiantEtudiantId(id);
	}

	@GetMapping("/homeWorkEtudiant/homeWork/id/{id}")
	public List<ReponseEtudiantHomeWork> findByHomeWorkEtudiantHomeWorkId(@PathVariable Long id) {
		return reponseEtudiantHomeWorkService.findByHomeWorkEtudiantHomeWorkId(id);
	}


	@Transactional
	@DeleteMapping("/homeWorkEtudiant/id/{id}")
	public int deleteByHomeWorkEtudiantId(@PathVariable Long id) {
		return reponseEtudiantHomeWorkService.deleteByHomeWorkEtudiantId(id);
	}

	@Transactional
	@DeleteMapping("/homeWorkEtudiant/etudiant/id/{id}")
	public int deleteByHomeWorkEtudiantEtudiantId(@PathVariable Long id) {
		return reponseEtudiantHomeWorkService.deleteByHomeWorkEtudiantEtudiantId(id);
	}

	@Transactional
	@DeleteMapping("/homeWorkEtudiant/homeWork/id/{id}")
	public int deleteByHomeWorkEtudiantHomeWorkId(@PathVariable Long id) {
		return reponseEtudiantHomeWorkService.deleteByHomeWorkEtudiantHomeWorkId(id);
	}

	@GetMapping("/")
	public List<ReponseEtudiantHomeWork> findAll() {
		return reponseEtudiantHomeWorkService.findAll();
	}


	@GetMapping("/homeWorkEtudiant/id/{id}/homeWorkEtudiant/reponse/question/numero/{numeroQuestion}")
	public List<ReponseEtudiantHomeWork> findByCriteria(@PathVariable Long idHomeWorkEtudiant, @PathVariable Long numeroQuestion) {
		return reponseEtudiantHomeWorkService.findByCriteria(idHomeWorkEtudiant, numeroQuestion);
	}

	@GetMapping("/QuestionId/{id}")
	public List<ReponseEtudiantHomeWork> findReponseEtudiantHomeWorkByQuestionId(@PathVariable Long id) {
		return reponseEtudiantHomeWorkService.findReponseEtudiantHomeWorkByQuestionId(id);
	}

	@Autowired
	private ReponseEtudiantHomeWorkService reponseEtudiantHomeWorkService;


}
