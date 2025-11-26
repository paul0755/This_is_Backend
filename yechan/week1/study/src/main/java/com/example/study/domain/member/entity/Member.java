package com.example.study.domain.member.entity;

import com.example.study.domain.common.BaseEntity;
import com.example.study.domain.member.enums.Gender;
import com.example.study.domain.member.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@DynamicUpdate
@DynamicInsert
// 위 두개는 INSERT와 UPDATE시 NULL인경우 그냥 쿼리 안보냄
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 40)
    private String password;

    @Column(nullable = false, length = 40)
    private  String address;

    private LocalDate inactiveDate;

    //@ColumnDefault("0") -> @Builder를 사용하면 적용안됨
    @Builder.Default
    private Integer point = 1;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    // TODO: N:M 관계 생성

    public void update(String name, String address, Gender gender){
        this.name = name;
        this.address = address;
        this.gender = gender;
    }
}

