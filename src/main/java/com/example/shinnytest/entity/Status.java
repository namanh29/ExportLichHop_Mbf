    package com.example.shinnytest.entity;

    import javax.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.ArrayList;
    import java.util.List;

    @NoArgsConstructor
    @Data@AllArgsConstructor
    @Entity
    public class Status {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String statusName;
        @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY,mappedBy = "status")
        private List<User> users;

        public void addUsers(User user){
            if(users==null){
                users=new ArrayList<>();
            }
            users.add(user);
        }

    }
