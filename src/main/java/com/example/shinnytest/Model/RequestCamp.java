package com.example.shinnytest.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCamp {
    private String code;
    private String name;
    private long status;

}
