package com.example.shinnytest.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String departmentName;
    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "department")
    private List<User> users;
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "department")
    private List<Position> positions;
    public void addUsers(User user){
        if(users==null){
            users=new ArrayList<>();
        }
        users.add(user);
    }
    public void  addPositions(Position position){
        if(positions == null){
            positions = new ArrayList<>();
        }
        positions.add(position);
    }
}
