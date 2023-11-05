package com.example.shinnytest.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    private String userName;
    private long status;
    private long departmentId;
    private long postionId;

}
