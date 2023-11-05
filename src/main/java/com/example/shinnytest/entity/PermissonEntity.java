package com.example.shinnytest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String text;
    private State state;

    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "parent",fetch = FetchType.EAGER)
    private List<PermissonEntity> children;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private PermissonEntity parent;
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "permissons")
    @JsonIgnore
    private List<User> users;
    public void addChildren(PermissonEntity childrens){
        if(children == null){
            children = new ArrayList<>();
        }
        children.add(childrens);
    }
}
