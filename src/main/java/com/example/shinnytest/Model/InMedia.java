package com.example.shinnytest.Model;

//import com.example.shinnytest.entity.User;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InMedia {
    private String ma_nguon;
    private long camp_id;
    private String ghi_chu;
    private long giao_cho;
    private String link;
}
