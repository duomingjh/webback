package com.example.videodownload.domain;

public class ImagBase {

    public String youth1;
    public String youth2;
    public String youth3;
    public String youth4;
    public String youth5;
    public String youth6;
    public String aged601;
    public String aged602;
    public String aged603;
    public String aged604;
    public String aged605;
    public String aged606;

    @Override
    public String toString() {
        return "ImagBase{" +
                "youth1='" + youth1 + '\'' +
                ", youth2='" + youth2 + '\'' +
                ", youth3='" + youth3 + '\'' +
                ", youth4='" + youth4 + '\'' +
                ", youth5='" + youth5 + '\'' +
                ", youth6='" + youth6 + '\'' +

                ", aged601='" + aged601 + '\'' +
                ", aged602='" + aged602 + '\'' +
                ", aged603='" + aged603 + '\'' +
                ", aged604='" + aged604 + '\'' +
                ", aged605='" + aged605 + '\'' +
                ", aged606='" + aged606 + '\'' +
                '}';
    }

    public ImagBase(String youth1, String youth2, String youth3, String youth4, String youth5, String youth6,  String aged601, String aged602, String aged603, String aged604, String aged605, String aged606) {
        this.youth1 = youth1;
        this.youth2 = youth2;
        this.youth3 = youth3;
        this.youth4 = youth4;
        this.youth5 = youth5;
        this.youth6 = youth6;

        this.aged601 = aged601;
        this.aged602 = aged602;
        this.aged603 = aged603;
        this.aged604 = aged604;
        this.aged605 = aged605;
        this.aged606 = aged606;
    }

    public String getYouth1() {
        return youth1;
    }

    public void setYouth1(String youth1) {
        this.youth1 = youth1;
    }

    public String getYouth2() {
        return youth2;
    }

    public void setYouth2(String youth2) {
        this.youth2 = youth2;
    }

    public String getYouth3() {
        return youth3;
    }

    public void setYouth3(String youth3) {
        this.youth3 = youth3;
    }

    public String getYouth4() {
        return youth4;
    }

    public void setYouth4(String youth4) {
        this.youth4 = youth4;
    }

    public String getYouth5() {
        return youth5;
    }

    public void setYouth5(String youth5) {
        this.youth5 = youth5;
    }

    public String getYouth6() {
        return youth6;
    }

    public void setYouth6(String youth6) {
        this.youth6 = youth6;
    }


    public String getAged601() {
        return aged601;
    }

    public void setAged601(String aged601) {
        this.aged601 = aged601;
    }

    public String getAged602() {
        return aged602;
    }

    public void setAged602(String aged602) {
        this.aged602 = aged602;
    }

    public String getAged603() {
        return aged603;
    }

    public void setAged603(String aged603) {
        this.aged603 = aged603;
    }

    public String getAged604() {
        return aged604;
    }

    public void setAged604(String aged604) {
        this.aged604 = aged604;
    }

    public String getAged605() {
        return aged605;
    }

    public void setAged605(String aged605) {
        this.aged605 = aged605;
    }

    public String getAged606() {
        return aged606;
    }

    public void setAged606(String aged606) {
        this.aged606 = aged606;
    }
}
