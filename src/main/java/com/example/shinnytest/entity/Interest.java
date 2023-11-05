package com.example.shinnytest.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String interestName;
}
