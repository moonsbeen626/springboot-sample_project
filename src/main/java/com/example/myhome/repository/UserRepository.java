package com.example.myhome.repository;

import com.example.myhome.model.Board;
import com.example.myhome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);//username을 찾아서 user반환 이때 username은 unique하게 관리
}
