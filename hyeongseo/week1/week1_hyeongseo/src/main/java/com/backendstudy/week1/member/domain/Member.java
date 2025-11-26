package com.backendstudy.week1.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원 도메인

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @Column(unique = true)
    private String email;

    private String password;
}
