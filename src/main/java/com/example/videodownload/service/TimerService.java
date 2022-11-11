package com.example.videodownload.service;

import com.example.videodownload.controller.ViewController;
import com.example.videodownload.domain.Persons;
import com.example.videodownload.mapper.AlFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableScheduling
public class TimerService {

    @Autowired
    AlFileMapper alFileMapper;

    @Autowired
    ViewController viewController;

    @Scheduled(cron =  "0 0 23 * * ?")
    private void Taskcount(){
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date();
        Persons a=new Persons(myFmt.format(date),viewController.dkCount, viewController.dyCount);
        alFileMapper.addUser(a);
        viewController.dyCount=0;
        viewController.dkCount=0;
        System.out.println(a.toString());
    }
    @Scheduled(cron =  "*/5 * * * * ?")
    private void Taskdiandeng(){
        if(System.currentTimeMillis()-viewController.start>10000){
            int iRandom = (int)(1+Math.random()*10);
            viewController.ranstr=String.valueOf(iRandom);


        }


    }
    @Scheduled(cron =  "0 0 0/2 * * ? ")
    private void Clear(){

            viewController.signature="";

    }
}
