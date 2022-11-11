package com.example.videodownload.domain;

public class PhoneCode {
    private String Phone;
    private String Code;
    private Long date;

    public PhoneCode() {
    }

    public PhoneCode(String phone, String code, Long date) {
        Phone = phone;
        Code = code;
        this.date = date;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
