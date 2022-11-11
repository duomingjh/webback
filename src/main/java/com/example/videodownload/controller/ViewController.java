package com.example.videodownload.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.videodownload.domain.AjaxResult;
import com.example.videodownload.domain.AlFile;
import com.example.videodownload.domain.Persons;
import com.example.videodownload.mapper.AlFileMapper;
import com.example.videodownload.service.IAlFileService;
import com.example.videodownload.service.PersonsService;
import com.example.videodownload.service.QRCodeService;
import com.example.videodownload.websocket.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;



@Controller

public class ViewController {

    @Autowired
    private QRCodeService qrCodeService;
    @Autowired
    private IAlFileService iAlFileService;
    @Value("${file-dir}")
    public String fileDir;
    @Value("${server-url}")
    public String serverUrl;
    @Autowired
    private MyWebSocket webSocket;

    @Autowired
    PersonsService personsService;
    String den="";
    boolean flag=false;
    public long start = 0;
    long randden = 0;
    int count=300;
    public String ranstr="";
    @Autowired
    AlFileMapper alFileMapper;


    public Integer dkCount=0;
    public  Integer dyCount=0;

    String nonce_str ="";
    String timestamp = "";
    String string1 = "";
    public String signature = "";
    //long start = System.currentTimeMillis();
    private static String [] extImgArr = {
            "bmp", "dib", "gif", "jfif", "jpe", "jpeg", "jpg",
            "png", "tif", "tiff", "ico"
    };



    @GetMapping("/view/{id}")
    public String add(@PathVariable("id") String id, ModelMap mmap) {
        AlFile alFile = iAlFileService.selectAlFileById(id);

        mmap.put("downloadUrl", serverUrl + "/download/" + id + "?id=" + UUID.randomUUID());
        mmap.put("fileUrl", alFile.getFileUrl());
        mmap.put("fileType", getFileType(alFile.getFileUrl()));
        mmap.put("id",id);
        if(alFile.getType()!=null&&!alFile.getType().equals("")&&alFile.getType().equals("mh")){
            mmap.put("type",alFile.getType());
            return "view/manhua";
        }
        if(alFile.getType()!=null&&!alFile.getType().equals("")&&alFile.getType().equals("dressing")){
            mmap.put("type",alFile.getType());
            return "view/gmhzj";
        }

        if(alFile.getId().contains("~")){

            return "view/index3";
        } else if(alFile.getFileUrl().contains("upload2")){
            return "view/index3";
        }else{
            return "view/index";

        }

    }
    @GetMapping("/view/test")
    public String Demotest(@RequestParam("id") String id,ModelMap mmap){
        System.out.println(id);
        AlFile alFile = iAlFileService.selectAlFileById(id);
        mmap.put("fileUrl", alFile.getFileUrl());
        return "view/mhview";
    }

    @GetMapping("/view/video/{id}")
    public String video(@PathVariable("id") String id, ModelMap mmap) {
        AlFile alFile = iAlFileService.selectAlFileById(id);
        mmap.put("downloadUrl", serverUrl + "/download/" + id);
        mmap.put("fileUrl", alFile.getFileUrl());
        return "view/video";
    }
    @GetMapping("/view/count")
    @ResponseBody
    public String count() {


        List<Persons> list=alFileMapper.selectf();
        String str="";
        for(int i=0;i<list.size();i++){
            String tem="日期"+list.get(i).getDate()+"        浏览人数："+list.get(i).getCount()+"      打印照片人数："+list.get(i).getCount1();
            str+=tem+"<br>";
        }

        return str;
    }

    @GetMapping("/view/hello")
    @ResponseBody
    public String video() {
        if(den.equals("")&&!ranstr.equals("")){
            System.out.println(ranstr);
            String temp=ranstr;
            ranstr="";
            return temp;
        }

        if(!den.equals("")){
            String temp=den;
            den="";
            ranstr="";
            return temp;

        }


        return "0";
    }


    //点灯
    @GetMapping("/view/dianden")
    @ResponseBody
    public String dd(@RequestParam("number") String number) {

        if(flag){
            if( System.currentTimeMillis()-start>5000){
                flag=false;
            }

        }
        if(den.equals("")&&!flag){
            System.out.println(number);
            den=number;
            start=System.currentTimeMillis();

            flag=true;
            count++;
            return String.valueOf(count);
        }else {

            return "no";
        }


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
    @GetMapping("/view/mode")
    public String getmode(){


        return  "view/viewMode";
    }
    @GetMapping("/view/modeindex")
    public String getmodeindex(){


        return  "view/modeindex";
    }
    @GetMapping("/view/webar")
    public String getwebar(){

        return  "view/webar";
    }
    @GetMapping("/view/zdf")
    public  String getpicShow(){

        return "view/zdf";
    }
    @GetMapping("/view/jmk")
    public  String getjmkShow(){

        return "view/jmk";
    }
    @GetMapping("/view/bt")
    public  String getbtShow(){
        dkCount++;
        return "view/bt";
    }
    @GetMapping("/hcsd")
    public  String gethcShow(Model model) throws NoSuchAlgorithmException {

        if(signature.equals("")){
            testGetRequest();

        }

        model.addAttribute("timestamp", timestamp);
        model.addAttribute("nonceStr", nonce_str);
        model.addAttribute("signature", signature);
        model.addAttribute("appid", "wx11c8d75834af1e6e");

        return "view/dianden";
    }
    @GetMapping("/view/hcsd")
    public  String gethcShow1(Model model) throws NoSuchAlgorithmException {


        return "view/tz";
    }


    public void testGetRequest() throws NoSuchAlgorithmException {

         nonce_str = create_nonce_str();
        timestamp = create_timestamp();
        string1 = "";
        signature = "";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx11c8d75834af1e6e&secret=5ea3c80ffcc5b0d626326ffa34371aed";
        //         请求客户端
        RestTemplate client = new RestTemplate();
        //      发起请求
        String body = client.getForEntity(url, String.class).getBody();
        JSONObject res= JSON.parseObject(body);
        String access_token= res.getString("access_token");
        System.out.println("******** Get请求 *********"+access_token);
        String url1 = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
       String body1 =client.getForEntity(url1,String.class).getBody();
        JSONObject res1= JSON.parseObject(body1);
        String ticket= res1.getString("ticket");
        string1 = "jsapi_ticket=" + ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + "http://makeshows.cn/hcsd";
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(string1.getBytes(StandardCharsets.UTF_8));
        signature = byteToHex(crypt.digest());

    }
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    @PostMapping("/view/add")
    @ResponseBody
    public AjaxResult save(@RequestBody HashMap<String, String> map) throws IOException {

        //System.out.println("书名：" + map.get("name"));
        // String pname= String.valueOf(System.currentTimeMillis());
        String pname=map.get("pname");
        if(GenerateImage(map.get("name").replace("data:image/jpeg;base64,",""),fileDir+"aa/"+pname+".jpg")){

            System.out.println(serverUrl+"/myFile/aa/"+pname+".jpg");
            //  webSocket.onMessage("print,"+serverUrl+"/myFile/aa/"+pname+".jpg",null);


        }
        return AjaxResult.success("ok",serverUrl+"/myFile/aa/"+pname+".jpg");
    }
    @PostMapping("/view/add1")
    @ResponseBody
    public AjaxResult save1(@RequestBody HashMap<String, String> map) throws IOException {
        String pname= String.valueOf(System.currentTimeMillis());
        String qc=UUID.randomUUID().toString();
        try {

            qrCodeService.createQRCode2File(serverUrl+"/myFile/aa/"+pname+".jpg", fileDir+"aa/"+qc+".jpg");
//            log.info("成功生成二维码！");
        } catch (Exception e) {
            e.printStackTrace();
//            log.error("发生错误， 错误信息是：{}！", e.getMessage());
        }
        return AjaxResult.success("ok",pname+","+serverUrl+"/myFile/aa/"+qc+".jpg");
    }
    @PostMapping("/view/add2")
    @ResponseBody
    public AjaxResult save2(@RequestBody HashMap<String, String> map) throws IOException {
        String url=map.get("imgurl");
        dyCount++;
        webSocket.onMessage("print,"+url,null);
        System.out.println(url);
        return AjaxResult.success("ok","666");
    }

    public static boolean GenerateImage(String base64Str, String imgFilePath) {
        if (base64Str == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(base64Str);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            //====
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
