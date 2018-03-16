package com.liang.manager.service.impl;

import com.liang.common.exception.UMallException;
import com.liang.common.pojo.DataTablesResult;
import com.liang.manager.mapper.TbThanksMapper;
import com.liang.manager.pojo.TbThanks;
import com.liang.manager.pojo.TbThanksExample;
import com.liang.manager.service.ThanksService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Liang
 */
@Service
public class ThanksServiceImpl implements ThanksService {

    @Autowired
    private TbThanksMapper tbThanksMapper;

    @Override
    public DataTablesResult getThanksList() {

        DataTablesResult result=new DataTablesResult();
        TbThanksExample example=new TbThanksExample();
        List<TbThanks> list=tbThanksMapper.selectByExample(example);
        if(list==null){
            throw new UMallException("获取捐赠列表失败");
        }
        result.setSuccess(true);
        result.setData(list);
        return result;
    }

    @Override
    public DataTablesResult getThanksListByPage(int page, int size) {

        DataTablesResult result=new DataTablesResult();
        TbThanksExample example=new TbThanksExample();
        if(page<=0) {
            page = 1;
        }
        PageHelper.startPage(page,size);
        List<TbThanks> list=tbThanksMapper.selectByExample(example);
        if(list==null){
            throw new UMallException("获取捐赠列表失败");
        }
        PageInfo<TbThanks> pageInfo=new PageInfo<>(list);

        for(TbThanks tbThanks:list){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = null;
            try {
                date = dateFormat.format(tbThanks.getDate());
            } catch (Exception e) {
                e.printStackTrace();
            }
            tbThanks.setTime(date);
        }

        result.setSuccess(true);
        result.setRecordsTotal((int) pageInfo.getTotal());
        result.setData(list);
        return result;
    }

    @Override
    public Long countThanks() {

        TbThanksExample example=new TbThanksExample();
        Long result=tbThanksMapper.countByExample(example);
        if(result==null){
            throw new UMallException("统计捐赠数目失败");
        }
        return result;
    }

    @Override
    public int addThanks(TbThanks tbThanks) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(tbThanks.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tbThanks.setDate(date);
        if(tbThanksMapper.insert(tbThanks)!=1){
            throw new UMallException("添加捐赠失败");
        }
        return 1;
    }

    @Override
    public int updateThanks(TbThanks tbThanks) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(tbThanks.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tbThanks.setDate(date);
        if(tbThanksMapper.updateByPrimaryKey(tbThanks)!=1){
            throw new UMallException("更新捐赠失败");
        }
        return 1;
    }

    @Override
    public int deleteThanks(int id) {

        if(tbThanksMapper.deleteByPrimaryKey(id)!=1){
            throw new UMallException("删除捐赠失败");
        }
        return 1;
    }
}
