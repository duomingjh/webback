package com.example.videodownload.service.impl;

import java.util.List;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.UUID;
import com.example.videodownload.controller.AlFileController;

import com.example.videodownload.service.QRCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.videodownload.mapper.AlFileMapper;
import com.example.videodownload.domain.AlFile;
import com.example.videodownload.service.IAlFileService;


/**
 * AlFileService业务层处理
 * 
 * @author test
 * @date 2021-04-23
 */
@Service
public class AlFileServiceImpl implements IAlFileService
{
    private static final Logger log = LoggerFactory.getLogger(AlFileController.class);

    @Value("${server-url}")
    public String serverUrl;

    @Value("${file-dir1}")
    public String fileDir1;

    @Value("${file-dir}")
    public String fileDir;

    @Value("${file-dir2}")
    public String fileDir2;
    @Autowired
    private AlFileMapper alFileMapper;



    @Autowired
    private QRCodeService qrCodeService;

    /**
     * 查询AlFile
     * 
     * @param id AlFileID
     * @return AlFile
     */
    @Override
    public AlFile selectAlFileById(String id)
    {
        return alFileMapper.selectAlFileById(id);
    }

    /**
     * 查询AlFile列表
     * 
     * @param alFile AlFile
     * @return AlFile
     */
    @Override
    public List<AlFile> selectAlFileList(AlFile alFile)
    {

        return alFileMapper.selectAlFileList(alFile);
    }

    /**
     * 新增AlFile
     * 
     * @param alFile AlFile
     * @return 结果
     */
    @Override
    public int insertAlFile(AlFile alFile)
    {
        return alFileMapper.insertAlFile(alFile);
    }

    /**
     * 修改AlFile
     * 
     * @param alFile AlFile
     * @return 结果
     */
    @Override
    public int updateAlFile(AlFile alFile)
    {
        return alFileMapper.updateAlFile(alFile);
    }

    /**
     * 上传文件
     * @param alFile
     * @return
     */
    @Override
    public String uploadAlFile(AlFile alFile,String name) {
        List<AlFile> list = selectAlFileList(alFile);
        if (list != null && list.size() > 0) {
            return list.get(0).getQrCodeUrl();
        }
        String id = UUID.fastUUID().toString(true);
        String viewUrl = serverUrl + "/view/" + id;
        log.info("二维码的内容链接------->" + viewUrl);

        String qrCodeFileUrl="";
        if(name!=null&&!name.equals("")){
            String qrCodeFileName = id+"~" + ".jpg";
            qrCodeService.createQRCode2File(viewUrl+"~",fileDir+name+"/"+qrCodeFileName);
            qrCodeFileUrl=serverUrl+"/myFile/"+name+"/"+qrCodeFileName;
            id=id+"~";
        }else{

            if(!alFile.getFileUrl().contains("upload2")){
                String qrCodeFileName = id+ ".jpg";
                qrCodeService.createQRCode2File(viewUrl, fileDir +fileDir1+ qrCodeFileName);
                qrCodeFileUrl = serverUrl + "/myFile/"+fileDir1 + qrCodeFileName;
            } else {
                String qrCodeFileName = id + ".jpg";
                qrCodeService.createQRCode2File(viewUrl, fileDir +fileDir2+ qrCodeFileName);
                qrCodeFileUrl = serverUrl + "/myFile/"+fileDir2 + qrCodeFileName;
            }
        }



        alFile.setId(id);
        alFile.setQrCodeUrl(qrCodeFileUrl);
        insertAlFile(alFile);
        return qrCodeFileUrl;
    }

    /**
     * 删除AlFile对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlFileByIds(String ids)
    {
        return alFileMapper.deleteAlFileByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除AlFile信息
     * 
     * @param id AlFileID
     * @return 结果
     */
    @Override
    public int deleteAlFileById(String id)
    {
        return alFileMapper.deleteAlFileById(id);
    }
}
