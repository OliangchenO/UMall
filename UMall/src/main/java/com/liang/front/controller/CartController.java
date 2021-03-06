package com.liang.front.controller;

import com.liang.common.pojo.Result;
import com.liang.common.utils.ResultUtil;
import com.liang.manager.dto.front.Cart;
import com.liang.manager.dto.front.CartProduct;
import com.liang.front.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "购物车")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/member/addCart",method = RequestMethod.POST)
    @ApiOperation(value = "添加购物车商品")
    public Result<Object> addCart(@RequestBody Cart cart){

        int result=cartService.addCart(cart.getUserId(),cart.getProductId(),cart.getProductNum());
        return new ResultUtil<Object>().setData(result);
    }

    @RequestMapping(value = "/member/cartList",method = RequestMethod.POST)
    @ApiOperation(value = "获取购物车商品列表")
    public Result<List<CartProduct>> getCartList(@RequestBody Cart cart){

        List<CartProduct> list=cartService.getCartList(cart.getUserId());
        return new ResultUtil<List<CartProduct>>().setData(list);
    }

    @RequestMapping(value = "/member/cartEdit",method = RequestMethod.POST)
    @ApiOperation(value = "编辑购物车商品")
    public Result<Object> updateCartNum(@RequestBody Cart cart){

        int result=cartService.updateCartNum(cart.getUserId(),cart.getProductId(),cart.getProductNum(),cart.getChecked());
        return new ResultUtil<Object>().setData(result);
    }

    @RequestMapping(value = "/member/editCheckAll",method = RequestMethod.POST)
    @ApiOperation(value = "是否全选购物车商品")
    public Result<Object> editCheckAll(@RequestBody Cart cart){

        int result=cartService.checkAll(cart.getUserId(),cart.getChecked());
        return new ResultUtil<Object>().setData(result);
    }

    @RequestMapping(value = "/member/cartDel",method = RequestMethod.POST)
    @ApiOperation(value = "删除购物车商品")
    public Result<Object> deleteCartItem(@RequestBody Cart cart){

        int result=cartService.deleteCartItem(cart.getUserId(),cart.getProductId());
        return new ResultUtil<Object>().setData(result);
    }
}
