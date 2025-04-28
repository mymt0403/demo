package com.example.demo.repository;

import com.example.demo.model.BulkyGarbageFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BulkyGarbageFacilityRepository extends JpaRepository<BulkyGarbageFacility, Integer> {

    @Query("SELECT b " +
            "FROM BulkyGarbageFacility b " +
            "INNER JOIN FETCH b.prefecture " +
            "WHERE b.prefecture.number = :prefectureNo")
    List<BulkyGarbageFacility> findByPrefectureNo(@Param("prefectureNo") Integer prefectureNo);
}
