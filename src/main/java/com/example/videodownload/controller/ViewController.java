package com.example.videodownload.controller;

import com.example.videodownload.domain.AlFile;
import com.example.videodownload.service.IAlFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.UUID;

@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private IAlFileService iAlFileService;

    @Value("${server-url}")
    public String serverUrl;

    private static String [] extImgArr = {
            "bmp", "dib", "gif", "jfif", "jpe", "jpeg", "jpg",
            "png", "tif", "tiff", "ico"
    };

    @GetMapping("/{id}")
    public String add(@PathVariable("id") String id, ModelMap mmap) {
        AlFile alFile = iAlFileService.selectAlFileById(id);
        System.out.println(alFile.getFileUrl());
        mmap.put("downloadUrl", serverUrl + "/download/" + id + "?id=" + UUID.randomUUID());
        mmap.put("fileUrl", alFile.getFileUrl());

        mmap.put("fileType", getFileType(alFile.getFileUrl()));
        mmap.put("id",id);
        if(alFile.getType()!=null&&!alFile.getType().equals("")&&alFile.getType().equals("mh")){
            mmap.put("type",alFile.getType());
            return "view/manhua";
        }

        if(alFile.getId().contains("~")){

            return "view/index3";
        } else if(alFile.getFileUrl().contains("upload2")){
            return "view/index3";
        }else{
            return "view/index";

        }

    }
    @GetMapping("/test")
    public String Demotest(@RequestParam("id") String id,ModelMap mmap){
        System.out.println(id);
        AlFile alFile = iAlFileService.selectAlFileById(id);
        mmap.put("fileUrl", alFile.getFileUrl());
        return "view/mhview";
    }

    @GetMapping("/video/{id}")
    public String video(@PathVariable("id") String id, ModelMap mmap) {
        AlFile alFile = iAlFileService.selectAlFileById(id);
        mmap.put("downloadUrl", serverUrl + "/download/" + id);
        mmap.put("fileUrl", alFile.getFileUrl());
        return "view/video";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String video() {
        return "hello,success~";
    }

//    public String getMimeType(String fileName) {
//        FileNameMap fileNameMap = URLConnection.getFileNameMap();
//        String type = fileNameMap.getContentTypeFor(fileName);
//        if(type.contains("image")){
//            return "image";
//        }
//        return "video";
//    }

    public String getFileType(String fileName){
        String tmpName = fileName.substring(fileName.lastIndexOf(".") + 1,
                fileName.length()).toLowerCase();
        for(String extStr : extImgArr){
            if(extStr.equals(tmpName)){
                return "image";
            }
        }
        return "video";
    }
    @GetMapping("/mode")
    public String getmode(){


        return  "view/viewMode";
    }

}
