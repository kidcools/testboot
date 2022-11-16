package com.kidcools.testboot.controller;


import com.kidcools.testboot.entity.Atlas;
import com.kidcools.testboot.service.IAtlasService;
import com.kidcools.testboot.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2022-10-05
 */
@Controller
@RequestMapping("/atlas")
public class AtlasController {


    @Autowired
    private IAtlasService atlasService;


    /**
     * 查询【请填写功能名称】列表
     */
    @PostMapping("/list")
    @ResponseBody
    public ResponseVo list(Atlas atlas) {

        List<Atlas> list = atlasService.selectAtlasList(atlas);
        return ResponseVo.getSuccessResponse(list);
    }
}
