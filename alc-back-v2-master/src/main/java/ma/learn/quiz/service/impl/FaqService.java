package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Faq;
import ma.learn.quiz.dao.FaqDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FaqService {

	@Autowired
	private FaqDao faqDao;

	public Optional<Faq> findById(Long id) {
		return faqDao.findById(id);
	}

	@Transactional
	public void deleteById(Long id) {
		faqDao.deleteById(id);
	}

	public List<Faq> findByFaqTypeId(Long id) {
		return faqDao.findByFaqTypeId(id);
	}

	@Transactional
	public int deleteByFaqTypeId(Long id) {
		return faqDao.deleteByFaqTypeId(id);
	}

	public int save(Faq faq) {
		if(findByFaqTypeId(faq.getFaqType().getId()) == null)
		{
			return -1;
		}
		else
		{
			faqDao.save(faq);
			return 1;
		}
		
	}


	public List<Faq> findAll() {
		return faqDao.findAll();
	}

	
}
