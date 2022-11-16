package com.kidcools.testboot.mapper;

import java.util.List;

import com.kidcools.testboot.entity.Atlas;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
//@Repository
public interface AtlasMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Atlas selectAtlasById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param atlas 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Atlas> selectAtlasList(Atlas atlas);

    /**
     * 新增【请填写功能名称】
     * 
     * @param atlas 【请填写功能名称】
     * @return 结果
     */
    public int insertAtlas(Atlas atlas);

    /**
     * 修改【请填写功能名称】
     * 
     * @param atlas 【请填写功能名称】
     * @return 结果
     */
    public int updateAtlas(Atlas atlas);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAtlasById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAtlasByIds(String[] ids);
}
