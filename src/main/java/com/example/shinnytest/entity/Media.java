package com.example.shinnytest.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ma_nguon;
    private String ghi_chu;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private Camp camp;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private User giao_cho;

    private String link;


}
