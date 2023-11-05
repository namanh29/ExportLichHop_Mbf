package com.example.shinnytest.Model;

import com.example.shinnytest.entity.Camp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import netscape.javascript.JSObject;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCamp<T> {
    private String message;
    private List<T> campList;
}
