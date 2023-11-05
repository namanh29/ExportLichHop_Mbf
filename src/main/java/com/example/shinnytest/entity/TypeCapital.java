package com.example.shinnytest.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeCapital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String typeCapitalName;
}
