package ma.learn.quiz.dao;

import ma.learn.quiz.bean.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryDao extends JpaRepository<Salary, Long> {


    Salary findSalaryByMoisAndAnneeAndProfId(int mois, int annee, Long id);

    List<Salary> findSalaryByAnneeAndProfId(int annee, Long id);

    List<Salary> findSalaryByAnnee(int annee);

    List<Salary> findSalaryByProfId(Long idprof);

    Salary findSalaryByMoisAndAnnee(int mois, int annee);

    Salary findSalaryById(Long id);
    List<Salary> findSalaryByPayer(boolean payer);

}