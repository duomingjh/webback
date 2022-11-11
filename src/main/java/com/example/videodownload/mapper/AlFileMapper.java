package com.example.videodownload.mapper;

import com.example.videodownload.domain.AlFile;
import com.example.videodownload.domain.Persons;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AlFileMapper接口
 * 
 * @author test
 * @date 2021-04-23
 */
@Mapper
public interface AlFileMapper 
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
     * 删除AlFile
     * 
     * @param id AlFileID
     * @return 结果
     */
    public int deleteAlFileById(String id);

    /**
     * 批量删除AlFile
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlFileByIds(String[] ids);

    public  List<Persons> selectf();
    public  void addUser(Persons persons);
}
