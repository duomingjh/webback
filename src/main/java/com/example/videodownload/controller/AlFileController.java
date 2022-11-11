package com.example.videodownload.controller;

import com.example.videodownload.domain.AjaxResult;
import com.example.videodownload.domain.AlFile;
import com.example.videodownload.service.IAlFileService;
import com.example.videodownload.service.QRCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/alFile")
public class AlFileController {
    private static final Logger log = LoggerFactory.getLogger(AlFileController.class);

    @Autowired
    private IAlFileService iAlFileService;

    @Autowired
    private QRCodeService qrCodeService;

    @Value("${server-url}")
    public String serverUrl;

    @Value("${file-dir}")
    public String fileDir;

    @Value("${file-dir1}")
    public String fileDir1;

    @Value("${file-dir2}")
    public String fileDir2;

    /**
     * 新增保存AlFile
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlFile alFile) throws FileNotFoundException {
        if (alFile == null || "".equals(alFile.getFileUrl())) {
            return AjaxResult.error("图片地址为空");
        }
        String qrCodeFileUrl = iAlFileService.uploadAlFile(alFile,null);
        return AjaxResult.success("success", qrCodeFileUrl);
    }


    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(MultipartFile file,String a) {

        if (file.isEmpty()) {

            return AjaxResult.error("上传失败，请选择文件");
        }

        String fileName = file.getOriginalFilename();
        File dest = new File(fileDir +fileDir1+ fileName);
        try {
            file.transferTo(dest);
            log.info("文件上传成功");
            String fileUrl = serverUrl + "/myFile/"+fileDir1+ fileName;
            AlFile alFile = new AlFile();
            alFile.setFileUrl(fileUrl);

            String qrCodeFileUrl = iAlFileService.uploadAlFile(alFile,null);
            return AjaxResult.success("success", qrCodeFileUrl);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return AjaxResult.error("图片上传失败");
    }

    @PostMapping("/upload2")
    @ResponseBody
    public AjaxResult upload2(MultipartFile file) {

        if (file.isEmpty()) {

            return AjaxResult.error("上传失败，请选择文件");
        }

        String fileName = file.getOriginalFilename();
        File dest = new File(fileDir +fileDir2+ fileName);
        try {
            file.transferTo(dest);
            log.info("文件上传成功");
            String fileUrl = serverUrl + "/myFile/"+fileDir2+ fileName;
            AlFile alFile = new AlFile();
            alFile.setFileUrl(fileUrl);
            System.out.println(fileUrl);
            String qrCodeFileUrl = iAlFileService.uploadAlFile(alFile,null);
            return AjaxResult.success("success", qrCodeFileUrl);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return AjaxResult.error("图片上传失败");
    }
@PostMapping("/uploadfile")
@ResponseBody
public AjaxResult uploadfile(MultipartFile file,String filename,String type){
        if(file.isEmpty()){
            return AjaxResult.error("上传文件失败，请选择文件");
        }
        File folder=new File(fileDir+filename);
        if(!folder.exists()&&!folder.isDirectory()){
            folder.mkdirs();
        }
        String fileNa=String.valueOf(System.currentTimeMillis());
        File dest=new File(fileDir+filename+"/"+fileNa+".png");
        try {
            file.transferTo(dest);
            String  fileUrl=serverUrl+"/myFile/"+filename+"/"+fileNa+".png";
            AlFile alFile=new AlFile();
            alFile.setFileUrl(fileUrl);
            alFile.setType(type);
            String  qrCodeFileUrl=iAlFileService.uploadAlFile(alFile,filename);
            return AjaxResult.success("success",alFile);
        }catch (IOException e){
            log.error(e.getMessage(), e);

        }
    return AjaxResult.error("图片上传失败");

}


    /**
     * 通过id查找资源（小程序使用）
     * @param id 资源id
     * @return
     */
    @GetMapping("/getById/{id}")
    @ResponseBody
    public AjaxResult getById(@PathVariable("id") String id) {
        AlFile alFile = iAlFileService.selectAlFileById(id);
        if(alFile != null){
            return AjaxResult.success("success", alFile);
        }
        return AjaxResult.error();
    }

    private String createQRCodeFilePath(String fileName) {
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(path.getAbsolutePath(), "/static/upload/");
            if (!upload.exists()) {
                upload.mkdirs();
            }
            return path.getAbsolutePath() + "/static/" + fileName;
        } catch (FileNotFoundException e) {
            log.error("创建upload目录失败" + e.getMessage());
        }
        return null;
    }
}
