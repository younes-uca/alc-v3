package ma.learn.quiz.dao;


import ma.learn.quiz.bean.TypeHomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeHomeWorkDao extends JpaRepository<TypeHomeWork, Long> {
    void deleteById(Long id);
    TypeHomeWork findByLib(String lib);
}