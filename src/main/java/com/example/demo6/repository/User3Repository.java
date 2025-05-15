package com.example.demo6.repository;

import com.example.demo6.entity.User3;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface User3Repository extends JpaRepository<User3, Long>, QuerydslPredicateExecutor<User3> {

    Optional<User3> findByMid(String mid);

    List<User3> findByMidContaining(String mid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User3 u set u.name=:name ,u.age=:age,u.gender=:gender,u.address=:address where u.mid=:mid ")
    void setUserUpdate(@Param("mid") String mid,@Param("name") String name,@Param("age") int age,@Param("gender") String gender,@Param("address") String address);

    List<User3> findAllByOrderByIdDesc();
}
