package com.example.videodownload.domain;

public class FnUser {
    private String Phone;
    private String name;
    private String wxname;
    private String date;
    private  String  company;
    private String job;
    private String prize;
    private  String read;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }


    public FnUser() {
    }

    public String getPrize() {
        return prize;
    }

    @Override
    public String toString() {
        return "FnUser{" +
                "Phone='" + Phone + '\'' +
                ", name='" + name + '\'' +
                ", wxname='" + wxname + '\'' +
                ", date='" + date + '\'' +
                ", company='" + company + '\'' +
                ", job='" + job + '\'' +
                ", prize='" + prize + '\'' +
                ", read='" + read + '\'' +
                '}';
    }

    public FnUser(String phone, String name, String wxname, String date, String company, String job, String prize, String read) {
        Phone = phone;
        this.name = name;
        this.wxname = wxname;
        this.date = date;
        this.company = company;
        this.job = job;
        this.prize = prize;
        this.read = read;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public FnUser(String phone, String name, String wxname, String date) {
        Phone = phone;
        this.name = name;
        this.wxname = wxname;
        this.date = date;
    }

    public FnUser(String phone, String name, String wxname, String date, String company) {
        Phone = phone;
        this.name = name;
        this.wxname = wxname;
        this.date = date;
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWxname() {
        return wxname;
    }

    public void setWxname(String wxname) {
        this.wxname = wxname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
