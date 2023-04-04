package ma.learn.quiz.dao;

import ma.learn.quiz.bean.SessionCours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SessionCoursDao extends JpaRepository<SessionCours, Long> {
    SessionCours findSessionCoursById(Long id);

    Optional<SessionCours> findSessionCoursByProfIdAndCoursIdAndGroupeEtudiantId(Long profid, Long coursid, Long groupEtudiant);

    SessionCours findSessionCoursByProfNom(String nom);

    int deleteSessionCoursById(Long id);


    List<SessionCours> findByProfId(Long id);

    List<SessionCours> findByGroupeEtudiantId(Long id);


    List<SessionCours> findSessionCoursBySalaryId(Long id);

    List<SessionCours> findSessionCoursByProfIdAndSalaryId(Long idprof, Long idsalary);
}