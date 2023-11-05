package com.example.shinnytest.Model;

import com.example.shinnytest.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAddUser {
    private String message ;
    private List<User> userList;
}
