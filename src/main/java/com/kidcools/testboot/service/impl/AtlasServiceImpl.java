package com.kidcools.testboot.service.impl;

import com.kidcools.testboot.entity.Atlas;
import com.kidcools.testboot.mapper.AtlasMapper;

import com.kidcools.testboot.service.IAtlasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-05
 */
@Service
public class AtlasServiceImpl implements IAtlasService
{

    @Autowired
    private AtlasMapper atlasMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Atlas selectAtlasById(Long id)
    {
        return atlasMapper.selectAtlasById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param atlas 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Atlas> selectAtlasList(Atlas atlas)
    {
        return atlasMapper.selectAtlasList(atlas);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param atlas 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAtlas(Atlas atlas)
    {
        return atlasMapper.insertAtlas(atlas);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param atlas 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAtlas(Atlas atlas)
    {
        return atlasMapper.updateAtlas(atlas);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAtlasByIds(String ids)
    {
        return atlasMapper.deleteAtlasByIds(ids.split(","));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAtlasById(Long id)
    {
        return atlasMapper.deleteAtlasById(id);
    }
}
