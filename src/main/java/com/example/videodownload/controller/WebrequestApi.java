package com.example.videodownload.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Controller
@RequestMapping("/webapi")
public class WebrequestApi {

public Queue<String> queue=new LinkedList<>();

@GetMapping("/mode")
public void remodenumer(@RequestParam("id") String id, HttpServletResponse httpServletResponse){
    try {
        queue.add(id);
        System.out.println(id);
        return;
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
}
@GetMapping("/client")
@ResponseBody
    public String Clientrequest(HttpServletResponse response){
    System.out.println(queue.peek());
    if(!queue.isEmpty()){
        return queue.poll();
    }
    return "null";

}

}
