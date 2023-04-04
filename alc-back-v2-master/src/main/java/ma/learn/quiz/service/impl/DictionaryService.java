package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Dictionary;
import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.dao.DictionaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DictionaryService {
	 @Autowired
	 private DictionaryDao dictionaryDao;
	 @Autowired
	 private EtudiantService etudiantService;
	public Dictionary findByWord(String word) {
		return dictionaryDao.findByWord(word);
	}

	public Dictionary findByWordAndEtudiantId(String word, Long id) {
		return dictionaryDao.findByWordAndEtudiantId(word, id);
	}

	public List<Dictionary> findByEtudiantId(Long id) {
		return dictionaryDao.findByEtudiantId(id);
	}

	public Dictionary findDictionaryById(Long id) {
		return dictionaryDao.findDictionaryById(id);
	}

	public List<Dictionary> findAll() {
		return dictionaryDao.findAll();
	}
	 
	 public Dictionary save(Dictionary dictionary ) {
		System.out.println(dictionary.getDefinition());
		 Etudiant etudiant = etudiantService.findEtudiantById(dictionary.getEtudiant().getId());
		 if(etudiant == null) {
			 return null;
		 }
			if(findDictionaryById(dictionary.getId())!=null) {
				return null;
			}
			if(findByWordAndEtudiantId(dictionary.getWord(), dictionary.getEtudiant().getId()) != null) {
				return null;
			}
			else {
				
				 dictionary.setEtudiant(etudiant);
				System.out.println("haddii hya"+dictionary);
				return dictionaryDao.save(dictionary);
			}
				
		}

		@Transactional
	public int deleteByWordAndEtudiantId(String word, Long id) {
		int wordDelete = dictionaryDao.deleteByWordAndEtudiantId(word, id);
		return wordDelete;
	}

	public int update(Dictionary dictionary) {
		Dictionary dict = findDictionaryById(dictionary.getId());
		if(dict == null){
			return -1;
		}else {
			dict.setWord(dictionary.getWord());
			dict.setDefinition(dictionary.getDefinition());
			dictionaryDao.save(dict);
			return 1;
		}
	}

	public int deleteDictionaryByEtudiantId(Long id) {
		return dictionaryDao.deleteDictionaryByEtudiantId(id);
	}
}