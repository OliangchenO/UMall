package com.liang.front.service.impl;

import com.liang.common.exception.UMallException;
import com.liang.common.jedis.JedisClient;
import com.liang.common.utils.QiniuUtil;
import com.liang.manager.dto.front.Member;
import com.liang.manager.mapper.TbMemberMapper;
import com.liang.manager.pojo.TbMember;
import com.liang.front.service.LoginService;
import com.liang.front.service.FrontMemberService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FrontFrontMemberServiceImpl implements FrontMemberService {

    @Autowired
    private LoginService loginService;
    @Autowired
    private TbMemberMapper tbMemberMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Override
    public String imageUpload(Long userId,String token,String imgData) {

        //过滤data:URL
        String base64=QiniuUtil.base64Data(imgData);
        String imgPath= QiniuUtil.qiniuBase64Upload(base64);

        TbMember tbMember=tbMemberMapper.selectByPrimaryKey(userId);
        if(tbMember==null){
            throw new UMallException("通过id获取用户失败");
        }
        tbMember.setFile(imgPath);
        if(tbMemberMapper.updateByPrimaryKey(tbMember)!=1){
            throw new UMallException("更新用户头像失败");
        }

        //更新缓存
        Member member=loginService.getUserByToken(token);
        member.setFile(imgPath);
        jedisClient.set("SESSION:" + token, new Gson().toJson(member));
        return imgPath;
    }
}
