package com.example.shinnytest.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long countDate;
    private boolean done;
    private Date fromDate;
    private Date toDate;
    private long payMoney;
    private long payNeed;
    private Date payDate;
    private long interestMoney;

}
