package com.example.shinnytest.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String positionName;
    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "position")
    private List<User> users;
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
    public void addUsers(User user){
        if(users==null){
            users=new ArrayList<>();
        }
        users.add(user);
    }
}
