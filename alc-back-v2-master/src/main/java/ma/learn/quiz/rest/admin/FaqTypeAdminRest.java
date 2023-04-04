package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.FaqType;
import ma.learn.quiz.service.impl.FaqTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/faqType")
public class FaqTypeAdminRest {

	@Autowired
	private FaqTypeService faqTypeService;

	@GetMapping("/id/{id}")
	public Optional<FaqType> findById(@PathVariable Long id) {
		return faqTypeService.findById(id);
	}

	@DeleteMapping("/id/{id}")
	public void deleteById(@PathVariable Long id) {
		faqTypeService.deleteById(id);
	}

	@GetMapping("/destinataire/{destinataire}")
	public List<FaqType> findByDestinataire(@PathVariable String destinataire) {
		return faqTypeService.findByDestinataire(destinataire);
	}

	@PostMapping("/")
	public int save(@RequestBody FaqType faqType) {
		return faqTypeService.save(faqType);
	}

	@GetMapping("/")
	public List<FaqType> findAll() {
		return faqTypeService.findAll();
	}

	
}
