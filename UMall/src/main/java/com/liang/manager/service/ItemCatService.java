package com.liang.manager.service;

import com.liang.common.pojo.ZTreeNode;
import com.liang.manager.pojo.TbItemCat;

import java.util.List;

/**
 * Created by Liang on 2017/8/2.
 */
public interface ItemCatService {

    TbItemCat getItemCatById(Long id);

    List<ZTreeNode> getItemCatList(int parentId);

    int addItemCat(TbItemCat tbItemCat);

    int updateItemCat(TbItemCat tbItemCat);

    void deleteItemCat(Long id);

    void deleteZTree(Long id);
}
