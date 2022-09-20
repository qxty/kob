package com.qxty.kob.controller.user;

import com.qxty.kob.mapper.UserMapper;
import com.qxty.kob.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping ("/user/all")
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable int userId) {
        return (User) userMapper.selectById(userId);
    }
    @GetMapping("/user/add/{userId}/{username}/{password}")
    public String addUser(@PathVariable int userId,
                          @PathVariable String username,
                          @PathVariable String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        User user = new User(userId, username, encode);
        userMapper.insert(user);
        return "Add User Success";
    }
    @GetMapping("/user/delete/{userId}")
    public String delete(@PathVariable String userId) {
        userMapper.deleteById(userId);
        return "Delete Success";
    }
}