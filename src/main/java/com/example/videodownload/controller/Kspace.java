package com.example.videodownload.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

@Controller
public class Kspace {

    String nonce_str ="";
    String timestamp = "";
    String string1 = "";
    public String signature = "";
    @GetMapping("kspace/index")
    public String index(Model model) throws NoSuchAlgorithmException {

            testGetRequest();



        model.addAttribute("timestamp", timestamp);
        model.addAttribute("nonceStr", nonce_str);
        model.addAttribute("signature", signature);
        model.addAttribute("appid", "wx11c8d75834af1e6e");

        return "view/kspace";

    }
    @GetMapping("kspace/viewer")
    public String index1(){


        return "view/viewer";

    }
    @GetMapping("kspace/video")
    public String videoview(){


        return "view/kspace/video";

    }
    @GetMapping("kspace/cjwt")
    public String cjwtview(){


        return "view/kspace/cjwt";

    }
    @GetMapping("kspace/cpys")
    public String cptsview(){


        return "view/kspace/cpys";

    }
    @GetMapping("kspace/jz")
    public String jzview(){


        return "view/kspace/jz";

    }

    @RequestMapping(value = "/preview", method = RequestMethod.GET)
    public void pdfStreamHandler(HttpServletRequest request, HttpServletResponse response) {
        //PDF文件地址
        File file = new File("D:\\1.pdf");
        if (file.exists()) {
            byte[] data = null;
            FileInputStream input=null;
            try {
                input= new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                response.getOutputStream().write(data);
            } catch (Exception e) {
                System.out.println("pdf文件处理异常：" + e);
            }finally{
                try {
                    if(input!=null){
                        input.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
        string1 = "jsapi_ticket=" + ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + "https://makeshows.top/kspace/index";
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(string1.getBytes(StandardCharsets.UTF_8));
        signature = byteToHex(crypt.digest());

    }
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
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
}
