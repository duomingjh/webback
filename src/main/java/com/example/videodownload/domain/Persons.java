package com.example.videodownload.domain;

public class Persons {
    private String date;
    private Integer count;
    private Integer count1;

    public Integer getCount1() {
        return count1;
    }

    public void setCount1(Integer count1) {
        this.count1 = count1;
    }

    public Persons(String date, Integer count, Integer count1) {
        this.date = date;
        this.count = count;
        this.count1 = count1;
    }

    public Persons(String date, Integer count) {
        this.date = date;
        this.count = count;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "date='" + date + '\'' +
                ", count=" + count +
                ", count1=" + count1 +
                '}';
    }
}
