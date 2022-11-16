package com.kidcools.testboot.mapper;


import com.kidcools.testboot.entity.C91video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 91视频Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-28
 */
@Mapper
public interface C91videoMapper 
{
    /**
     * 查询91视频
     * 
     * @param id 91视频主键
     * @return 91视频
     */
    public C91video selectC91videoById(Long id);

    /**
     * 查询91视频列表
     * 
     * @param c91video 91视频
     * @return 91视频集合
     */
    public List<C91video> selectC91videoList(C91video c91video);

    /**
     * 新增91视频
     * 
     * @param c91video 91视频
     * @return 结果
     */
    public int insertC91video(C91video c91video);

    /**
     * 修改91视频
     * 
     * @param c91video 91视频
     * @return 结果
     */
    public int updateC91video(C91video c91video);

    /**
     * 删除91视频
     * 
     * @param id 91视频主键
     * @return 结果
     */
    public int deleteC91videoById(Long id);

    /**
     * 批量删除91视频
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteC91videoByIds(String[] ids);
}
