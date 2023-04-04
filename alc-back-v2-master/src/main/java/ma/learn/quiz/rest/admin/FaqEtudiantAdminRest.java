package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.FaqEtudiant;
import ma.learn.quiz.bean.FaqProf;
import ma.learn.quiz.service.impl.FaqEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/faqEtudiant")
public class FaqEtudiantAdminRest {

	@Autowired
	private FaqEtudiantService faqEtudiantService;

	@GetMapping("/")
	public List<FaqEtudiant> findAll() {
		return faqEtudiantService.findAll();
	}

	@GetMapping("/id/{id}")
	public Optional<FaqEtudiant> findById(@PathVariable Long id) {
		return faqEtudiantService.findById(id);
	}

	@DeleteMapping("/id/{id}")
	public void deleteById(@PathVariable Long id) {
		faqEtudiantService.deleteById(id);
	}

	@GetMapping("/faqType/id/{id}")
	public List<FaqEtudiant> findByFaqTypeId(@PathVariable Long id) {
		return faqEtudiantService.findByFaqTypeId(id);
	}

	@DeleteMapping("/faqType/id/{id}")
	public int deleteByFaqTypeId(@PathVariable Long id) {
		return faqEtudiantService.deleteByFaqTypeId(id);
	}

	@GetMapping("/etudiant/id/{id}")
	public List<FaqEtudiant> findByEtudiantId(@PathVariable Long id) {
		return faqEtudiantService.findByEtudiantId(id);
	}

	@DeleteMapping("/etudiant/id/{id}")
	public int deleteByEtudiantId(@PathVariable Long id) {
		return faqEtudiantService.deleteByEtudiantId(id);
	}

	@GetMapping("/libelle/{libelle}")
	public FaqEtudiant findByLibelle(@PathVariable String libelle) {
		return faqEtudiantService.findByLibelle(libelle);
	}

	@PostMapping("/")
	public int save(@RequestBody FaqEtudiant faqEtudiant) {
		return faqEtudiantService.save(faqEtudiant);
	}

	@GetMapping("/critere/etudiant/{idEtudiant}/type/{idType}")
	public List<FaqProf> findByCritere(@PathVariable Long idEtudiant, @PathVariable Long idType) {
		return faqEtudiantService.findByCritere(idEtudiant, idType);
	}

	@PutMapping("/")
	public void update(@RequestBody FaqEtudiant faqEtudiant) {
		faqEtudiantService.update(faqEtudiant);
	}

	
	
}
