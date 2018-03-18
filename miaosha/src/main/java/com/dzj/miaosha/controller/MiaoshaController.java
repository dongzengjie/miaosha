package com.dzj.miaosha.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dzj.miaosha.dto.Expose;
import com.dzj.miaosha.entity.OrderInfo;
import com.dzj.miaosha.entity.User;
import com.dzj.miaosha.result.Result;
import com.dzj.miaosha.service.GoodsService;
import com.dzj.miaosha.service.MiaoshaService;
import com.dzj.miaosha.service.OrderService;
import com.dzj.miaosha.vo.GoodsVo;

@RestController
@RequestMapping(value = "/miaosha")
public class MiaoshaController {

	@Autowired
	private MiaoshaService miaoshaService;

	@Autowired
	private OrderService orderService;
	@Autowired
	private GoodsService goodsService;

	@PostMapping(value = "/{goodsId}/{md5}/domiaosha")
	public Result<Map<String, Object>> domiaosha(@PathVariable("goodsId") Long goodsId, @PathVariable("md5") String md5,
			User user) {
		Map<String, Object> modelmap= new HashMap<String,Object>();
		if(user ==null) {
			modelmap.put("success", false);
			modelmap.put("errorMsg", "请登陆");
		}
		GoodsVo goodsVo =goodsService.getGoodsByGoodsId(goodsId);
		OrderInfo orderInfo= miaoshaService.miaosha(user, goodsVo, md5);
		modelmap.put("success", true);
		modelmap.put("orderInfo", orderInfo);
	
		return 	Result.success(modelmap);

	}

	/**
	 * qps:114
	 * @param goodsId
	 * @return
	 */
	@PostMapping(value = "/{goodsId}/getMd5")
	public Result<Expose> getMd5(@PathVariable("goodsId") Long goodsId) {
		Expose expose = miaoshaService.getMiaosha_Key(goodsId);
		return 	Result.success(expose);

	}

}
