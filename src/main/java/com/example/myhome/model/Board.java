package com.example.myhome.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //데이터 베이스와 매핑
@Data //getter, setter 자동 설정
public class Board {

    @Id //키 값임
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment설정
    private long id;

    @NotNull
    private String title;
    private String content;


}
