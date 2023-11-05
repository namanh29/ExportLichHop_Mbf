package com.example.shinnytest.Model;

import com.example.shinnytest.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUpdate {
    private String message;
    private User user;
}
