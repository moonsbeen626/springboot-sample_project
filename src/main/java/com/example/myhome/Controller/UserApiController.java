package com.example.myhome.Controller;

import com.example.myhome.model.Board;
import com.example.myhome.model.User;
import com.example.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // user/api url과 매핑
public class UserApiController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users") //모든 user데이터 조회
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/users") //데이터 생성
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single item

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}") //데이터 수정
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return repository.findById(id)//id로 user를 조회함
                .map(user -> {
//                    User.setTitle(newUser.getTitle());
//                    User.setContent(newUser.getContent());
                    user.getBoards().clear();
                    user.getBoards().addAll(newUser.getBoards());
                    for(Board board : user.getBoards()) {
                        board.setUser(user);
                    }
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}") //데이터 삭제
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

