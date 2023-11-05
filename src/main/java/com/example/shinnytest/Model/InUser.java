package com.example.shinnytest.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InUser {
    private String userName;
    private String password;
    private long status;
    private long positionId;
    private long departmentId;
    private String email;
    private long managerId;
    private String fullName;
    private String phone;
    public InUser(String fullName, String email, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }




}
