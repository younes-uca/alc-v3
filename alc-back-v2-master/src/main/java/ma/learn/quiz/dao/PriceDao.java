package ma.learn.quiz.dao;

import ma.learn.quiz.bean.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceDao extends JpaRepository<Price, Long> {
    @Query(value = "SELECT * FROM `price` WHERE for_group=:isGroup ORDER BY price ASC LIMIT 1", nativeQuery = true)
    Price findMinPrice(@Param("isGroup") boolean isGroup);

    @Query(value = "SELECT * FROM `price` WHERE for_group=:isGroup ORDER BY price DESC  LIMIT 1", nativeQuery = true)
    Price findMaxPrice(@Param("isGroup") boolean isGroup);
}
