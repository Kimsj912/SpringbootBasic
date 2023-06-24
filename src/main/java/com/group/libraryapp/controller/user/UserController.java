package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
//        users.add(new User(request.getName(), request.getAge()));
        String sql = "Insert into user (name, age) values (?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());

    }

    @GetMapping("/user")
    public List<UserResponse> getUsers(){
//        List<UserResponse> responses = new ArrayList<>();
//        for (int i = 0; i < users.size(); i++) {
//            responses.add(new UserResponse(i + 1, users.get(i)));
//        }
//        return responses;

        String sql = "Select * from user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            // user정보를 UserResponse에 맞게 변환해준다.

            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        String read = "select * from user where id = ?";
        boolean isUserNotExist = jdbcTemplate.query(read, (rs, rowNum) -> 0, request.getId()).isEmpty();
        if (isUserNotExist) throw new IllegalArgumentException();

        String sql = "update user set name = ? where id = ?";
        jdbcTemplate.update(sql, request.getName(), request.getId());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestBody String name){
        String read = "select * from user where name = ?";
        boolean isUserNotExist = jdbcTemplate.query(read, (rs, rowNum) -> 0, name).isEmpty();
        if (isUserNotExist) throw new IllegalArgumentException();

        String sql = "Delete from user where name = ?";
        jdbcTemplate.update(sql, name);
        System.out.println(name + " :: name is deleted");
    }
}
