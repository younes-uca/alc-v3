package ma.learn.quiz.dao;

import ma.learn.quiz.bean.InviteStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviteStudentdDao extends JpaRepository<InviteStudent, Long> {
    List<InviteStudent> findInviteStudentByEtudiantId(Long id);

    InviteStudent findInviteStudentByEmailInvited(String emailInvited);

    InviteStudent findInviteStudentByEmailInvitedAndCode(String emailInvited, String code);

}
