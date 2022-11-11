package com.example.videodownload.service;

import com.example.videodownload.domain.dataCount;

import java.util.List;

public interface IdataCountService {
    public List<dataCount> allSelectlist();

    public List<dataCount> allSelectType(String type);

    public dataCount slectDateAndType(String date,String type);

    public int insert(String date,String type,Integer number);

    public  int update(String date,String type,Integer number);
}
