package com.example.videodownload.service;

import com.example.videodownload.domain.FnUser;

import java.util.List;

public interface IFnUserService {
    public List<FnUser> SelectAll();
    public FnUser SelectByPhone(String phone);
    public  FnUser SelectByName(String name);
    public  FnUser SelectByPrize(String prize);
    public int insert(String name,String wxname,String phone,String company,String date,String prize,String read,String job);
    public int update(String name,String wxname,String phone,String company,String date,String prize,String read,String job);
}
