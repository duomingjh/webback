package com.example.videodownload.controller;

import com.example.videodownload.domain.dataCount;
import com.example.videodownload.service.IdataCountService;
import com.example.videodownload.service.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PanoramaController {
     @Autowired
    IdataCountService idataCountService;

     @Autowired
    IpUtil ipUtil;


    @GetMapping("panorama/index")
    public String Panorama() {

        return "view/qjpic/qjt";
    }
    @GetMapping("/")
    public String index(HttpServletRequest request) {

        ipUtil.getIpAddr(request);
        //System.out.println( ip+"=="+ipUtil.getCityInfo(ip));
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date();
        String d= myFmt.format(date);
        dataCount dataCount= idataCountService.slectDateAndType(myFmt.format(date),"全景图" );
        if(dataCount==null){
            dataCount data=new dataCount(d,"全景图",1);
            idataCountService.insert(data.getDate(),data.getType(),data.getNumber());
        }else {
            dataCount.setNumber(dataCount.getNumber()+1);
            idataCountService.update(dataCount.getDate(),dataCount.getType(),dataCount.getNumber());
        }

        return "view/qjpic/index";
    }
    @GetMapping("panorama/introduce")
    public String introduce() {

        return "view/qjpic/js";
    }


}
