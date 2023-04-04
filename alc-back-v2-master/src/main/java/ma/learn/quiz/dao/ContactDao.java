package ma.learn.quiz.dao;

import ma.learn.quiz.bean.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDao extends JpaRepository<Contact, Long> {

    List<Contact> findByEmail(String email);
}
