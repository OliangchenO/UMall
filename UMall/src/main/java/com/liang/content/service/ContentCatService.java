package com.liang.content.service;

import com.liang.common.pojo.ZTreeNode;
import com.liang.manager.dto.ContentCatDto;
import com.liang.manager.pojo.TbContentCategory;

import java.util.List;

public interface ContentCatService {

    TbContentCategory getContentCatById(Long id);

    List<ZTreeNode> getContentCatList(Long parentId);

    int addContentCat(ContentCatDto contentCatDto);

    int updateContentCat(ContentCatDto contentCatDto);

    int deleteContentCat(Long id);
}
