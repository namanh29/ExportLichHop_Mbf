package com.example.shinnytest.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPermissonId {
    private List<Long> ids;
        private long user_id;

}
