package com.example.myhome.repository;

import com.example.myhome.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> { //<가져올 데이터 타입, 키값의 타입>

    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title, String content);
}
