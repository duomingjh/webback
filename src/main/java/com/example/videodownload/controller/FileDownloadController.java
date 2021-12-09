package com.example.videodownload.controller;

import com.example.videodownload.domain.AlFile;
import com.example.videodownload.service.IAlFileService;
import com.example.videodownload.service.impl.AlFileServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Controller
public class FileDownloadController {

    private static final Logger log = LoggerFactory.getLogger(FileDownloadController.class);

    @Autowired
    private IAlFileService iAlFileService;

//    @RequestMapping("/download/{id}")
//    public String downloadFile(@PathVariable("id") String id, HttpServletRequest request,
//                               HttpServletResponse response) throws UnsupportedEncodingException {
//
//        // 获取指定目录下的第一个文件
//        File scFileDir = new File("E://test");
//        File TrxFiles[] = scFileDir.listFiles();
//        System.out.println(TrxFiles[0]);
//        String fileName = TrxFiles[0].getName(); //下载的文件名
//
//        // 如果文件名不为空，则进行下载
//        if (fileName != null) {
//            //设置文件路径
//            String realPath = "E://test/";
//            File file = new File(realPath, fileName);
//
//            // 如果文件名存在，则进行下载
//            if (file.exists()) {
//
//                // 配置文件下载
//                response.setHeader("content-type", "application/octet-stream");
//                response.setContentType("application/octet-stream");
//                // 下载文件能正常显示中文
//                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//
//                // 实现文件下载
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try {
//                    fis = new FileInputStream(file);
//                    bis = new BufferedInputStream(fis);
//                    OutputStream os = response.getOutputStream();
//                    int i = bis.read(buffer);
//                    while (i != -1) {
//                        os.write(buffer, 0, i);
//                        i = bis.read(buffer);
//                    }
//                    System.out.println("Download the song successfully!");
//                }
//                catch (Exception e) {
//                    System.out.println("Download the song failed!");
//                }
//                finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }

    /**
     * 新增保存AlFile
     */
    @GetMapping("/add")
    @ResponseBody
    public String addSave() {
        AlFile alFile = new AlFile();
        alFile.setFileUrl("http://localhost:1111/123.mp4");
        alFile.setQrCodeUrl("http://test.com");
        iAlFileService.insertAlFile(alFile);
        return "success";
    }

    @RequestMapping("/download/{id}")
    public void netDownLoadNet(@PathVariable("id") String id, HttpServletResponse response) {
        AlFile alFile = iAlFileService.selectAlFileById(id);
        if (alFile == null) {
            return;
        }
        String filename = alFile.getFileUrl().substring(alFile.getFileUrl().lastIndexOf("/") + 1);
        InputStream inputStream = null;
        try {
            URL url = new URL(alFile.getFileUrl());
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(60000);
            inputStream = conn.getInputStream();

            response.reset();
//            response.setContentType(conn.getContentType());

            // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");

            //纯下载方式 文件名应该编码成UTF-8
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));

            byte[] buffer = new byte[1024];
            int len;
            OutputStream outputStream = response.getOutputStream();
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
                outputStream.flush();
            }

        } catch (Exception e) {
            log.error(e.getMessage());
//            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("关闭inputStream失败："+e.getMessage());
                }
            }
        }

    }
}
