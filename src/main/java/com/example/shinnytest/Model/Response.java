package com.example.shinnytest.Model;

import com.example.shinnytest.entity.Camp;
import com.example.shinnytest.entity.PermissonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Response<T> {
    private String message;
    private List<T> data;

    public Response(String message, List<T> data) {
        this.message = message;
        this.data = data;
    }

    public Response(String message) {

        this.message = message;
    }

}
