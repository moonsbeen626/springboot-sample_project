package com.example.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore //json으로 표시할 때 role이 가지고 있는 user를 표시하지 않음.
    private List<User> users;
}