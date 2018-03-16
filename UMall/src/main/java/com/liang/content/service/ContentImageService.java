package com.liang.content.service;

import com.liang.common.pojo.DataTablesResult;
import com.liang.manager.pojo.TbImage;

public interface ContentImageService {

    TbImage getContentImageById(Long id);

    DataTablesResult getContentImage();

    int updateContentImage(TbImage tbImage);
}
