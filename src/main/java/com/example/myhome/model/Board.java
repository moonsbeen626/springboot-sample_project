package com.example.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity //데이터 베이스와 매핑
@Data //getter, setter 자동 설정
public class Board {

    @Id //키 값임
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment설정
    private long id;

    @NotNull
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")//조인할 컬럼 이름
    @JsonIgnore
    private User user;
}
