package com.example.videodownload.service;


import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ft.v20200304.FtClient;
import com.tencentcloudapi.ft.v20200304.models.AgeInfo;
import com.tencentcloudapi.ft.v20200304.models.ChangeAgePicRequest;
import com.tencentcloudapi.ft.v20200304.models.ChangeAgePicResponse;
import com.tencentcloudapi.ft.v20200304.models.FaceRect;
import com.tencentcloudapi.iai.v20180301.models.DetectFaceResponse;
import org.springframework.stereotype.Service;
@Service
public class Bianlao {


    public ChangeAgePicResponse addtyear(String base , DetectFaceResponse resp, FtClient client1,Long year) throws TencentCloudSDKException {

        ChangeAgePicRequest req1 = new ChangeAgePicRequest();
        req1.setImage(base);
        //req1.setUrl(base);


        AgeInfo[] ageInfos1 ;
        AgeInfo ageInfo1 = new AgeInfo();
        AgeInfo ageInfo2 = new AgeInfo();
        ageInfo1.setAge(year);
        FaceRect faceRect1 = new FaceRect();
        faceRect1.setY(resp.getFaceInfos()[0].getY());
        faceRect1.setX(resp.getFaceInfos()[0].getX());
        faceRect1.setWidth(resp.getFaceInfos()[0].getWidth());
        faceRect1.setHeight(resp.getFaceInfos()[0].getHeight());
        ageInfo1.setFaceRect(faceRect1);
         if(resp.getFaceInfos().length>1){
             ageInfo2.setAge(year);
             FaceRect faceRect2 = new FaceRect();
             faceRect2.setY(resp.getFaceInfos()[1].getY());
             faceRect2.setX(resp.getFaceInfos()[1].getX());
             faceRect2.setWidth(resp.getFaceInfos()[1].getWidth());
             faceRect2.setHeight(resp.getFaceInfos()[1].getHeight());
             ageInfo2.setFaceRect(faceRect2);
             ageInfos1 = new AgeInfo[2];
             ageInfos1[1] = ageInfo2;
         }else {
             ageInfos1 = new AgeInfo[1];

         }

        ageInfos1[0] = ageInfo1;
        //ageInfos1[1] = ageInfo2;
        req1.setAgeInfos(ageInfos1);
        req1.setRspImgType("url");
        ChangeAgePicResponse resp1 = client1.ChangeAgePic(req1);
        System.out.println(ChangeAgePicResponse.toJsonString(resp1).toString());
        // 返回的resp是一个ChangeAgePicResponse的实例，与请求对象对应
        return resp1;
    }
}
