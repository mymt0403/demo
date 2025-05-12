package com.example.demo.repository;

import com.example.demo.entity.Prefecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrefectureRepository extends JpaRepository<Prefecture, Integer> {}
