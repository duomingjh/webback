package com.example.videodownload.controller;

import com.alibaba.fastjson.JSON;
import com.example.videodownload.domain.dataCount;
import com.example.videodownload.service.IdataCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DataCountController {
    @Autowired
    IdataCountService idataCountService;


    @GetMapping("dataCount/insert")
    @ResponseBody
    public String data(@RequestParam(value = "type") String type){
        if(type==null&&type.equals("")){
            System.out.println("type错误");
            return "erro";
        }
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date();
        String d= myFmt.format(date);
        dataCount dataCount= idataCountService.slectDateAndType(myFmt.format(date),type );
        if(dataCount==null){
            dataCount data=new dataCount(d,type,1);
            idataCountService.insert(data.getDate(),data.getType(),data.getNumber());
        }else {
             dataCount.setNumber(dataCount.getNumber()+1);
           idataCountService.update(dataCount.getDate(),dataCount.getType(),dataCount.getNumber());
        }

        return "succes";
    }

    @GetMapping("dataCount/select")
    @ResponseBody
    public String slectAll(@RequestParam(value = "type") String type){
        if(type==null&&type.equals("")){
            System.out.println("type错误");
            return "erro";
        }
        List<dataCount> list=idataCountService.allSelectType(type);
        String str="";
        for(int i=0;i<list.size();i++){
            String tem="日期"+list.get(i).getDate()+"        浏览人数："+list.get(i).getNumber();
            str+=tem+"<br>";
        }
        return JSON.toJSONString(list);
    }
}
