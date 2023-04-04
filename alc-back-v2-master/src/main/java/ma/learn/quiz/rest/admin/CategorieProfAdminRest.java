package ma.learn.quiz.rest.admin;

import ma.learn.quiz.bean.CategorieProf;
import ma.learn.quiz.service.impl.CategorieProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/categorieprof")
public class CategorieProfAdminRest {
	@Autowired
	private CategorieProfService categorieProfservice;

	 @GetMapping("/")
	public List<CategorieProf> findAll() {
		return categorieProfservice.findAll();
	}

	@DeleteMapping("/{id}")
	public int deleteCategorieProfById(@PathVariable Long id) {
		return categorieProfservice.deleteCategorieProfById(id);
	}

	@PostMapping("/")
	public CategorieProf save(@RequestBody CategorieProf entity) {
		return categorieProfservice.save(entity);
	}
}
