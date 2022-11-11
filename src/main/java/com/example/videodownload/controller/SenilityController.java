package com.example.videodownload.controller;

import com.example.videodownload.domain.AjaxResult;
import com.example.videodownload.domain.ImagBase;
import com.example.videodownload.service.Bianlao;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.facefusion.v20181201.FacefusionClient;
import com.tencentcloudapi.facefusion.v20181201.models.FaceRect;
import com.tencentcloudapi.facefusion.v20181201.models.FuseFaceRequest;
import com.tencentcloudapi.facefusion.v20181201.models.FuseFaceResponse;
import com.tencentcloudapi.facefusion.v20181201.models.MergeInfo;
import com.tencentcloudapi.ft.v20200304.FtClient;
import com.tencentcloudapi.ft.v20200304.models.ChangeAgePicResponse;
import com.tencentcloudapi.iai.v20180301.IaiClient;
import com.tencentcloudapi.iai.v20180301.models.DetectFaceRequest;
import com.tencentcloudapi.iai.v20180301.models.DetectFaceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SenilityController {

  @Autowired
    Bianlao bianlao;

    @PostMapping("img1")
    @ResponseBody
    public AjaxResult Tximg1(String base,String year) throws TencentCloudSDKException, InterruptedException {
        Credential cred = new Credential("AKIDioiiZIqAHOUSJ2A9ya6W8WmvvjIgvwjM", "ZyAB9D8ta9Yq6CB0pW4dUDqVFuNgBgaW");
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("iai.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 实例化要请求产品的client对象,clientProfile是可选的
        IaiClient client = new IaiClient(cred, "", clientProfile);
        // 实例化一个请求对象,每个接口都会对应一个request对象
        DetectFaceRequest req = new DetectFaceRequest();
        req.setMaxFaceNum(2L);
        req.setImage(base);
        req.setNeedFaceAttributes(1L);
        // 返回的resp是一个DetectFaceResponse的实例，与请求对象对应
        DetectFaceResponse resp = client.DetectFace(req);
        // 输出json格式的字符串回包
        System.out.println(DetectFaceResponse.toJsonString(resp));
        HttpProfile httpProfile1 = new HttpProfile();
        httpProfile1.setEndpoint("ft.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile1 = new ClientProfile();
        clientProfile1.setHttpProfile(httpProfile1);
        // 实例化要请求产品的client对象,clientProfile是可选的
        FtClient client1 = new FtClient(cred, "ap-guangzhou", clientProfile1);
        ChangeAgePicResponse r3 = bianlao.addtyear(base,resp, client1,Long.valueOf((year)));
        return AjaxResult.success("ok",r3.getResultUrl());
    }

    @PostMapping("img")
    @ResponseBody
    public AjaxResult Tximg(String base,String ModeID1,String man1,String wom1,String ModeID2,String man2,String wom2,String ModeID3,String man3,String wom3,String ModeID4,String man4,String wom4,String ModeID5,String man5,String wom5,String ModeID6,String man6,String wom6) throws TencentCloudSDKException, InterruptedException {
        Credential cred = new Credential("AKIDioiiZIqAHOUSJ2A9ya6W8WmvvjIgvwjM", "ZyAB9D8ta9Yq6CB0pW4dUDqVFuNgBgaW");
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("iai.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 实例化要请求产品的client对象,clientProfile是可选的
        IaiClient client = new IaiClient(cred, "", clientProfile);
        // 实例化一个请求对象,每个接口都会对应一个request对象
        DetectFaceRequest req = new DetectFaceRequest();
        req.setMaxFaceNum(2L);
        req.setImage(base);
        req.setNeedFaceAttributes(1L);
        // 返回的resp是一个DetectFaceResponse的实例，与请求对象对应
        DetectFaceResponse resp = client.DetectFace(req);
        // 输出json格式的字符串回包
        System.out.println(DetectFaceResponse.toJsonString(resp));
        HttpProfile httpProfile3 = new HttpProfile();
        httpProfile3.setEndpoint("facefusion.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile3 = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile3);
        // 实例化要请求产品的client对象,clientProfile是可选的
        FacefusionClient client3 = new FacefusionClient(cred, "ap-guangzhou", clientProfile3);
        // 实例化一个请求对象,每个接口都会对应一个request对象
        FuseFaceResponse resp11 = getFuseFaceResponse(base, ModeID1, man1, wom1, resp, client3);
        Thread.sleep(800);
        FuseFaceResponse resp12 = getFuseFaceResponse(base, ModeID2, man2, wom2, resp, client3);
        Thread.sleep(800);
        FuseFaceResponse resp13 = getFuseFaceResponse(base, ModeID3, man3, wom3, resp, client3);
        Thread.sleep(800);
        FuseFaceResponse resp14 = getFuseFaceResponse(base, ModeID4, man4, wom4, resp, client3);
        Thread.sleep(800);
        FuseFaceResponse resp15 = getFuseFaceResponse(base, ModeID5, man5, wom5, resp, client3);
        Thread.sleep(800);
        FuseFaceResponse resp16 = getFuseFaceResponse(base, ModeID6, man6, wom6, resp, client3);
        // 输出json格式的字符串回包

       HttpProfile httpProfile1 = new HttpProfile();
        httpProfile1.setEndpoint("ft.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile1 = new ClientProfile();
        clientProfile1.setHttpProfile(httpProfile1);
        // 实例化要请求产品的client对象,clientProfile是可选的
        FtClient client1 = new FtClient(cred, "ap-guangzhou", clientProfile1);


        HttpProfile httpProfile5 = new HttpProfile();
        httpProfile5.setEndpoint("iai.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile5 = new ClientProfile();
        clientProfile5.setHttpProfile(httpProfile5);
        // 实例化要请求产品的client对象,clientProfile是可选的
        IaiClient client5 = new IaiClient(cred, "", clientProfile5);
        // 实例化一个请求对象,每个接口都会对应一个request对象
        ChangeAgePicResponse r31 = getChangeAgePicResponse(resp11, client1, client5);
        ChangeAgePicResponse r32 = getChangeAgePicResponse(resp12, client1, client5);
        ChangeAgePicResponse r33 = getChangeAgePicResponse(resp13, client1, client5);
        ChangeAgePicResponse r34 = getChangeAgePicResponse(resp14, client1, client5);
        ChangeAgePicResponse r35 = getChangeAgePicResponse(resp15, client1, client5);
        ChangeAgePicResponse r36 = getChangeAgePicResponse(resp16, client1, client5);

        // 实例化一个请求对象,每个接口都会对应一个request对象
        ImagBase img=new ImagBase(resp11.getFusedImage(),resp12.getFusedImage(),resp13.getFusedImage(),resp14.getFusedImage(),resp15.getFusedImage(),resp16.getFusedImage(), r31.getResultUrl(), r32.getResultUrl(), r33.getResultUrl(), r34.getResultUrl(), r35.getResultUrl(), r36.getResultUrl());
        // 输出json格式的字符串回包
       // System.out.println(img.toString());
        return  AjaxResult.success("ok",img);
    }

    private ChangeAgePicResponse getChangeAgePicResponse(FuseFaceResponse resp11, FtClient client1, IaiClient client5) throws TencentCloudSDKException {
        DetectFaceRequest req5 = new DetectFaceRequest();
        req5.setMaxFaceNum(2L);
    //    req5.setImage(resp11.getFusedImage());
        req5.setUrl(resp11.getFusedImage());

        // 返回的resp是一个DetectFaceResponse的实例，与请求对象对应
        DetectFaceResponse resp5 = client5.DetectFace(req5);
        // 输出json格式的字符串回包
        System.out.println(DetectFaceResponse.toJsonString(resp5));

        // 返回的resp是一个DetectFaceResponse的实例，与请求对象对应


        // 返回的resp是一个DetectFaceResponse的实例，与请求对象对应
        // ChangeAgePicResponse r1 = bianlao.addtyear(resp3.getFusedImage(),resp5,client1,40L);
        //  ChangeAgePicResponse r2 = bianlao.addtyear(resp3.getFusedImage(),resp5,client1,60L);
        ChangeAgePicResponse r3 = bianlao.addtyear(resp11.getFusedImage(),resp5, client1,80L);
        return r3;
    }

    private FuseFaceResponse getFuseFaceResponse(String base, String ModeID, String man, String wom, DetectFaceResponse resp, FacefusionClient client3) throws TencentCloudSDKException {
        FuseFaceRequest req3 = new FuseFaceRequest();
        req3.setProjectId("312725");
        req3.setModelId(ModeID);
        req3.setRspImgType("url");
        req3.setFuseProfileDegree(1L);
        req3.setFuseFaceDegree(1L);

        MergeInfo[] mergeInfos1 = new MergeInfo[2];
        MergeInfo mergeInfo1 = new MergeInfo();
        MergeInfo mergeInfo2 = new MergeInfo();
        FaceRect faceRect3=new FaceRect();
        FaceRect faceRect4=new FaceRect();
        if(resp.getFaceInfos()[0].getFaceAttributesInfo().getGender()>51){
            faceRect3.setX(resp.getFaceInfos()[0].getX());
            faceRect3.setY(resp.getFaceInfos()[0].getY());
            faceRect3.setWidth(resp.getFaceInfos()[0].getWidth());
            faceRect3.setHeight(resp.getFaceInfos()[0].getHeight());
            mergeInfo1.setInputImageFaceRect(faceRect3);
            mergeInfo1.setTemplateFaceID(man);
            mergeInfo1.setImage(base);

        }else {
            faceRect3.setX(resp.getFaceInfos()[0].getX());
            faceRect3.setY(resp.getFaceInfos()[0].getY());
            faceRect3.setWidth(resp.getFaceInfos()[0].getWidth());
            faceRect3.setHeight(resp.getFaceInfos()[0].getHeight());
            mergeInfo1.setInputImageFaceRect(faceRect3);
            mergeInfo1.setTemplateFaceID(wom);
            mergeInfo1.setImage(base);
        }

        if(resp.getFaceInfos()[1].getFaceAttributesInfo().getGender()<50){
            faceRect4.setX(resp.getFaceInfos()[1].getX());
            faceRect4.setY(resp.getFaceInfos()[1].getY());
            faceRect4.setWidth(resp.getFaceInfos()[1].getWidth());
            faceRect4.setHeight(resp.getFaceInfos()[1].getHeight());
            mergeInfo2.setInputImageFaceRect(faceRect4);
            mergeInfo2.setTemplateFaceID(wom);
            mergeInfo2.setImage(base);


        }
       else {
           faceRect4.setX(resp.getFaceInfos()[1].getX());
           faceRect4.setY(resp.getFaceInfos()[1].getY());
           faceRect4.setWidth(resp.getFaceInfos()[1].getWidth());
           faceRect4.setHeight(resp.getFaceInfos()[1].getHeight());
           mergeInfo2.setInputImageFaceRect(faceRect4);
           mergeInfo2.setTemplateFaceID(man);
           mergeInfo2.setImage(base);

       }

        mergeInfos1[0]=mergeInfo1;
        mergeInfos1[1]=mergeInfo2;
        req3.setMergeInfos(mergeInfos1);

        // 返回的resp是一个FuseFaceResponse的实例，与请求对象对应
        FuseFaceResponse resp3 = client3.FuseFace(req3);
        return resp3;
    }


}
