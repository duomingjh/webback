package com.example.videodownload.domain;

public class dataCount {
    private String date;
    private String type;
    private  int number;

    public dataCount(String date, String type, int number) {
        this.date = date;
        this.type = type;
        this.number = number;
    }

    public dataCount() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "dataCount{" +
                "date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", number=" + number +
                '}';
    }
}
