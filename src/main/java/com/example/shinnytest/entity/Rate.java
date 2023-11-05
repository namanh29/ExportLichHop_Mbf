package com.example.shinnytest.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type_rate;
}
