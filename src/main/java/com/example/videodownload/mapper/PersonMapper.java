package com.example.videodownload.mapper;

import com.example.videodownload.domain.Persons;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper {
    public List<Persons> SeclectAll();
}
