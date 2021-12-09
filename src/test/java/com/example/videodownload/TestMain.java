package com.example.videodownload;

public class TestMain {

    public static void main(String[] args) {
        String str = "http://localhost:8080/123.mp4";
        System.out.println(str.substring(str.lastIndexOf("/") + 1));
    }
}
