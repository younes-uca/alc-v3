package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.FaqProf;
import ma.learn.quiz.dao.FaqProfDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class FaqProfService {

	@Autowired
	private FaqProfDao faqProfDao;
	public FaqProf findByLibelle(String libelle) {
		return faqProfDao.findByLibelle(libelle);
	}

	@Autowired
	private EntityManager entityManager;

	public List<FaqProf> findAll() {
		return faqProfDao.findAll();
	}

	public Optional<FaqProf> findById(Long id) {
		return faqProfDao.findById(id);
	}
	
	public void update(FaqProf faqProf) {
		faqProf.setAdmin(faqProf.getAdmin());
		faqProf.setDescription(faqProf.getDescription());
		faqProfDao.save(faqProf);
		
	}
	

	@Transactional
	public void deleteById(Long id) {
		faqProfDao.deleteById(id);
	}

	public List<FaqProf> findByFaqTypeId(Long id) {
		return faqProfDao.findByFaqTypeId(id);
	}
	
	 public List<FaqProf> findByCritere(Long idProf, Long idType)
		{
			String query = "SELECT f FROM FaqProf f WHERE f.prof.id= '"+idProf+"' and f.faqType.id='"+idType+"'";
			return entityManager.createQuery(query).getResultList();
		}

	@Transactional
	public int deleteByFaqTypeId(Long id) {
		return faqProfDao.deleteByFaqTypeId(id);
	}

	public List<FaqProf> findByProfId(Long id) {
		return faqProfDao.findByProfId(id);
	}

	@Transactional
	public int deleteByProfId(Long id) {
		return faqProfDao.deleteByProfId(id);
	}

	public int save(FaqProf faqProf) {
			faqProfDao.save(faqProf);
			return 1; 
	}

	
	
}
