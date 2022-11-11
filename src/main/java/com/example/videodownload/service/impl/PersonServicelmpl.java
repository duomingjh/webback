package com.example.videodownload.service.impl;

import com.example.videodownload.domain.Persons;
import com.example.videodownload.mapper.PersonMapper;
import com.example.videodownload.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServicelmpl implements PersonsService {
    @Autowired
    PersonMapper personMapper;

    @Override
    public List<Persons> getAllusers() {
        return personMapper.SeclectAll();
    }
}
