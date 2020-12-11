package com.example.myhome.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity //데이터 베이스와 매핑
@Data //getter, setter 자동 설정
public class Role {

    @Id //키 값임
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment설정
    private long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}