package com.example.shinnytest.Miami;

public enum CriticalLever {
    CO_LANH_DAO ("COLANHDAO"),
    KHONG_LANH_DAO ("KHONGLANHDAO"),
    QUAN_TRONG ("QUANTRONG");

    private String value;
    CriticalLever() {
    }

    CriticalLever(String value) {
        this.value = value;
    }
}
