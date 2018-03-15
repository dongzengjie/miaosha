package com.dzj.miaosha.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dzj.miaosha.entity.User;
import com.dzj.miaosha.result.Result;
import com.dzj.miaosha.service.GoodsService;
import com.dzj.miaosha.vo.GoodsVo;

@RestController
@RequestMapping(value="/goods")
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	@GetMapping(value="/getGoodsList")
	public Result<Map<String, Object>> getGoodsList(User user) {
		Map<String, Object> modelMap =new HashMap<String,Object>();
		modelMap.put("goodslist", goodsService.getGoodsList());
		modelMap.put("user", user);
		return Result.success(modelMap);
		
	}
	@GetMapping(value="/getGoodsById/{goodsId}")
	public Map<String, Object> getGoodsById(@PathVariable("goodsId") long goodsId,User user){
		Map<String, Object> modelMap =new HashMap<String,Object>();
		GoodsVo goodsVo =goodsService.getGoodsByGoodsId(goodsId);
		Date date =new Date();
		modelMap.put("nowtime", date);
		modelMap.put("goods", goodsVo);
		return modelMap;
		
	}
}
