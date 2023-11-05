package com.example.shinnytest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;

    private String name;
    private String email;


    private String phone;
    private String passWord;

    private String positionName;
    private String departmentName;
    private String statusName;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private User managers;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference

    private Position position;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference

    private Department department;
    @JsonBackReference

    @ManyToOne(fetch = FetchType.LAZY)
    private Status status;
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "permisson_user",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permisson_id",referencedColumnName = "id") )
    private List<PermissonEntity> permissons;
    public void addPermissons(PermissonEntity permisson){
        if(permissons == null){
            permissons = new ArrayList<>();
        }
        permissons.add(permisson);
    }
}
