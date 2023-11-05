package com.example.shinnytest.Model;

import com.example.shinnytest.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseSearch {
    private String messgage;

    public ResponseSearch(String messgage) {
        this.messgage = messgage;
    }
    private List<User> users;
}
