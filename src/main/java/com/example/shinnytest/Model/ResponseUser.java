package com.example.shinnytest.Model;

import com.example.shinnytest.entity.PermissonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {
    private String message;
    private List<PermissonEntity> data;

    public ResponseUser(String message) {
        this.message = message;
    }
}
