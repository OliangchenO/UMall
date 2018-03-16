package com.liang.content.service;

import com.liang.common.pojo.AllGoodsResult;
import com.liang.common.pojo.DataTablesResult;
import com.liang.manager.dto.front.ProductDet;
import com.liang.manager.dto.front.ProductHome;
import com.liang.manager.pojo.TbContent;


public interface ContentService {

    int addContent(TbContent content);

    DataTablesResult getContentListByCid(Long cid);

    int deleteContent(Long id);

    int updateContent(TbContent content);

    TbContent getContentById(Long id);

    ProductHome getProductHome();

    ProductDet getProductDet(Long id);

    AllGoodsResult getAllProduct(int page, int size, String sort, int priceGt, int priceLte);
}
