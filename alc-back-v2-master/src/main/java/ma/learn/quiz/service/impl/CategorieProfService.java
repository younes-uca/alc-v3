package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.CategorieProf;
import ma.learn.quiz.dao.CategorieProfDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategorieProfService {
    @Autowired
    private CategorieProfDao categorieProfDao;

    @Transactional
    public int deleteCategorieProfById(Long id) {
        return categorieProfDao.deleteCategorieProfById(id);
    }

    public List<CategorieProf> findAll() {
        return categorieProfDao.findAll();
    }


    public CategorieProf save(CategorieProf entity) {
        return categorieProfDao.save(entity);
    }
}
