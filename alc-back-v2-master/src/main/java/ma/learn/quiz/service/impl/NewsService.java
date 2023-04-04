package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.News;
import ma.learn.quiz.dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsDao newsDao;
    @Autowired
    private EntityManager entityManager;

    public News findByRef(String ref) {
        return newsDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        return newsDao.deleteByRef(ref);
    }

    public int save(News news) {
        if (findByRef(news.getRef()) != null) {
            return -1;
        } else {
            newsDao.save(news);
            return 1;
        }

    }

    public List<News> findAll() {
        return newsDao.findAll();
    }

    public List<News> findByCritere(String destinataire) {
        String query = "SELECT n From News n where NOW() > n.dateDebut and NOW() < n.dateFin and n.destinataire = '" + destinataire + "' ORDER BY numeroOrdre";
        return entityManager.createQuery(query).getResultList();
    }

    public void update(News news) {
        news.setDescription(news.getDescription());
        news.setImage(news.getImage());
        news.setLibelle(news.getLibelle());
        news.setDateDebut(news.getDateDebut());
        newsDao.save(news);
    }
}
