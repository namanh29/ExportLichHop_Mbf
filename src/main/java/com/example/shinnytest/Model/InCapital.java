package com.example.shinnytest.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InCapital {
    private String fullName;
    private long money;
    private String phone;
    private long periodFee;
    private long timeBorrow;
    private long typeRate;
    private long typeCapital;
    private long wallet_id;
    private long rate;
    private long typeInterest;;
}
