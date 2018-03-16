package com.liang.manager.mapper;

import com.liang.manager.pojo.TbImageExample;
import com.liang.manager.pojo.TbImage;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbImageMapper {
    long countByExample(TbImageExample example);

    int deleteByExample(TbImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbImage record);

    int insertSelective(TbImage record);

    List<TbImage> selectByExample(TbImageExample example);

    TbImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbImage record, @Param("example") TbImageExample example);

    int updateByExample(@Param("record") TbImage record, @Param("example") TbImageExample example);

    int updateByPrimaryKeySelective(TbImage record);

    int updateByPrimaryKey(TbImage record);
}