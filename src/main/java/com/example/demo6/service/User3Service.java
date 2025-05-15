package com.example.demo6.service;

import com.example.demo6.dto.User3Dto;
import com.example.demo6.entity.User3;
import com.example.demo6.repository.User3Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class User3Service {

    private final User3Repository user3Repository;

    public List<User3> getUserList() {
        // return user3Repository.findAll(); //전체조회(입력순 조회)
        return user3Repository.findAllByOrderByIdDesc();  //전체조회(아이디 내림차순)
    }

    public Optional<User3> getUserIdSearch(String mid) {
        return user3Repository.findByMid(mid);
    }

    public void setUserInput(User3 user3) {
        user3Repository.save(user3);
    }

    public void setUserDelete(Long id) {
        user3Repository.deleteById(id);
    }

    public List<User3> getUserMidSearch(String mid) {
        return user3Repository.findByMidContaining(mid);
    }

    public void setUserUpdate(User3 user3) {
        user3Repository.setUserUpdate(
                user3.getMid(),
                user3.getName(),
                user3.getAge(),
                user3.getGender(),
                user3.getAddress()
        );
    }


//    public User3Service(User3Repository user3Repository) {
//        this.user3Repository = user3Repository;
//    }
}
