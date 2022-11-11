package com.example.videodownload.service.impl;

import com.example.videodownload.domain.FnUser;
import com.example.videodownload.mapper.FnUserMapper;
import com.example.videodownload.service.IFnUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FnUserServiceImpI  implements IFnUserService {
    @Autowired
    FnUserMapper fnUserMapper;

    @Override
    public List<FnUser> SelectAll() {
        return fnUserMapper.SelectAll();
    }

    @Override
    public FnUser SelectByPhone(String phone) {
        return fnUserMapper.SelectByPhone(phone);
    }

    @Override
    public FnUser SelectByName(String name) {
        return fnUserMapper.SelectByName(name);
    }

    @Override
    public FnUser SelectByPrize(String prize) {
        return fnUserMapper.SelectByPrize(prize);
    }

    @Override
    public int insert(String name,String wxname,String phone,String company,String date,String prize,String read,String job) {
       return fnUserMapper.insert(name,wxname,phone,company,date,prize,read,job);
    }

    @Override
    public int update(String name,String wxname,String phone,String company,String date,String prize,String read,String job) {
        return fnUserMapper.update(name,wxname,phone,company,date,prize,read,job);
    }
}
