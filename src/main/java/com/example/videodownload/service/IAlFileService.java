package com.example.videodownload.service;

import java.util.List;
import com.example.videodownload.domain.AlFile;

/**
 * AlFileService接口
 * 
 * @author test
 * @date 2021-04-23
 */
public interface IAlFileService 
{
    /**
     * 查询AlFile
     * 
     * @param id AlFileID
     * @return AlFile
     */
    public AlFile selectAlFileById(String id);

    /**
     * 查询AlFile列表
     * 
     * @param alFile AlFile
     * @return AlFile集合
     */
    public List<AlFile> selectAlFileList(AlFile alFile);

    /**
     * 新增AlFile
     * 
     * @param alFile AlFile
     * @return 结果
     */
    public int insertAlFile(AlFile alFile);

    /**
     * 修改AlFile
     * 
     * @param alFile AlFile
     * @return 结果
     */
    public int updateAlFile(AlFile alFile);

    /**
     * 上传数据文件
     * @param alFile
     * @return
     */
    public String uploadAlFile(AlFile alFile,String name);

    /**
     * 批量删除AlFile
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlFileByIds(String ids);

    /**
     * 删除AlFile信息
     * 
     * @param id AlFileID
     * @return 结果
     */
    public int deleteAlFileById(String id);
}
