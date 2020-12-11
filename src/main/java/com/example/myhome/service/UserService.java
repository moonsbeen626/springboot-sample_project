package com.example.myhome.service;

import com.example.myhome.model.Role;
import com.example.myhome.model.User;
import com.example.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        String endocecPassword = passwordEncoder.encode(user.getPassword()); //사용자가 전달한 비밀번호를 암호화
        user.setPassword(endocecPassword);
        user.setEnabled(true); //권한 활성화
        Role role = new Role();
        role.setId(1);
        user.getRoles().add(role);
        return userRepository.save(user);
    }
}
