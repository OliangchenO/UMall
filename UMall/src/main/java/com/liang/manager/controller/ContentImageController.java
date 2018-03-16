package com.liang.manager.controller;

import com.liang.common.pojo.DataTablesResult;
import com.liang.common.pojo.Result;
import com.liang.common.utils.ResultUtil;
import com.liang.content.service.ContentImageService;
import com.liang.manager.pojo.TbImage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "图片列表信息")
public class ContentImageController {

    @Autowired
    private ContentImageService contentImageService;

    @RequestMapping(value = "/image/list",method = RequestMethod.GET)
    @ApiOperation(value = "通过获得图片列表")
    public DataTablesResult getContentImage(){

        DataTablesResult result=contentImageService.getContentImage();
        return result;
    }

    @RequestMapping(value = "/image/update",method = RequestMethod.POST)
    @ApiOperation(value = "编辑图片")
    public Result<Object> updateContentImage(@ModelAttribute TbImage tbImage){

        contentImageService.updateContentImage(tbImage);
        return new ResultUtil<Object>().setData(null);
    }
}
