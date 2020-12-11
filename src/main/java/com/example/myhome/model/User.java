package com.example.myhome.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //데이터 베이스와 매핑
@Data //getter, setter 자동 설정
public class User {

    @Id //키 값임
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment설정
    private long id;

    private String username;
    private String password;
    private Boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "user_role", //조인할 테이블 이름
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
}
