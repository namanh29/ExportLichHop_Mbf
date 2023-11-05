package com.example.shinnytest.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Capital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String full_name;
    private String phone;
    private long money;
    private long time_borrow;
    private boolean isInterestBefore;
    private long rate;
    private long totalInterestPaid;
    private Date donated_date;
    private String typeCapitalName;
    private String typeRateName;
    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private List<PaymentSchedule> paymentSchedule;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Interest interestType;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Rate typeRate;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private TypeCapital typeCapital;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Wallet wallet;
    private String walletName;
    private long periodFee;
    private long totalMoneyCurrent;
    private Date next_date;
}
