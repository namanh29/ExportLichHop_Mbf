package com.example.shinnytest.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data@AllArgsConstructor
@NoArgsConstructor
public class Camp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameCamp;
    private String code;
    private long status;
    private Date created_at;

}
