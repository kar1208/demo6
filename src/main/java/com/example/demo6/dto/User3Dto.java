package com.example.demo6.dto;

import com.example.demo6.entity.User3;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User3Dto {
    private Long id;
    private String mid;
    private String name;
    private int age;
    private String gender;
    private String address;

    // Entity to dto
    public static  User3Dto createUser(Optional<User3> user3) {
        User3Dto dto = User3Dto.builder()
                .id(user3.get().getId())
                .mid(user3.get().getMid())
                .name(user3.get().getName())
                .age(user3.get().getAge())
                .gender(user3.get().getGender())
                .address(user3.get().getAddress())
                .build();
        return dto;

    }
}
