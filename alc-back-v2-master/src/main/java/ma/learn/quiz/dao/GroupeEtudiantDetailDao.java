package ma.learn.quiz.dao;

import ma.learn.quiz.bean.GroupeEtudiantDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupeEtudiantDetailDao extends JpaRepository<GroupeEtudiantDetail, Long> {
    List<GroupeEtudiantDetail> findByGroupeEtudiantId(Long id);

    GroupeEtudiantDetail findByGroupeEtudiantIdAndEtudiantId(long groupId, long studentId);

    List<GroupeEtudiantDetail> findByEtudiantId(Long id);

    int deleteByGroupeEtudiantId(Long id);

    int deleteGroupeEtudiantDetailById(Long id);

    int deleteGroupeEtudiantDetailByEtudiantId(Long id);

}
