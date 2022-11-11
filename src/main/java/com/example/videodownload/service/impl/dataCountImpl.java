package com.example.videodownload.service.impl;

import com.example.videodownload.domain.dataCount;
import com.example.videodownload.mapper.dataCountMapper;
import com.example.videodownload.service.IdataCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class dataCountImpl implements IdataCountService {
    @Autowired
    dataCountMapper dataCountMapper;

    @Override
    public List<dataCount> allSelectlist() {
        return dataCountMapper.allSelectlist();
    }

    @Override
    public List<dataCount> allSelectType(String type) {
        return dataCountMapper.allSelectType(type);
    }

    @Override
    public dataCount slectDateAndType(String date, String type) {
        return dataCountMapper.slectDateAndType(date,type);
    }

    @Override
    public int insert(String date, String type, Integer number) {
        return dataCountMapper.insert(date,type,number);
    }

    @Override
    public int update(String date, String type, Integer number) {
        return dataCountMapper.update(date,type,number);
    }
}
