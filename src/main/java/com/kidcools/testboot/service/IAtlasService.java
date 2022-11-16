package com.kidcools.testboot.service;


import com.kidcools.testboot.entity.Atlas;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2022-10-05
 */
public interface IAtlasService 
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
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteAtlasByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAtlasById(Long id);
}
