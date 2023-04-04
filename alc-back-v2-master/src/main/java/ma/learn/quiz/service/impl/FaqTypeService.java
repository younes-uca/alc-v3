package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.FaqType;
import ma.learn.quiz.dao.FaqTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FaqTypeService {

	@Autowired
	private FaqTypeDao faqTypeDao;

	public Optional<FaqType> findById(Long id) {
		return faqTypeDao.findById(id);
	}

	@Transactional
	public void deleteById(Long id) {
		faqTypeDao.deleteById(id);
	}

	public List<FaqType> findByDestinataire(String destinataire) {
		return faqTypeDao.findByDestinataire(destinataire);
	}

	public int save(FaqType faqType) {
		faqTypeDao.save(faqType);
		return 1;
	}

	public List<FaqType> findAll() {
		return faqTypeDao.findAll();
	}
	
}
