package com.dzj.miaosha.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dzj.miaosha.dto.Expose;
import com.dzj.miaosha.entity.OrderInfo;
import com.dzj.miaosha.entity.User;
import com.dzj.miaosha.rabbitmq.MQSender;
import com.dzj.miaosha.rabbitmq.MiaoshaMessage;
import com.dzj.miaosha.redis.GoodsKey;
import com.dzj.miaosha.redis.RedisService;
import com.dzj.miaosha.result.CodeMsg;
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

	@Autowired
	private RedisService redisService;
	
	@Autowired
	private MQSender mqSender;
	
	
	
	@PostMapping(value = "/{goodsId}/{md5}/domiaosha")
	public Result<Integer> domiaosha(@PathVariable("goodsId") Long goodsId, @PathVariable("md5") String md5,
			User user) {
		Map<String, Object> modelmap= new HashMap<String,Object>();
		if(user ==null) {
			modelmap.put("success", false);
			modelmap.put("errorMsg", "请登陆");
		}
		GoodsVo goodsVo =goodsService.getGoodsByGoodsId(goodsId);
		// 预减库存
		miaoshaService.advancereduceStocks(user, goodsVo, md5);
		
		MiaoshaMessage miaoshaMessage =new MiaoshaMessage();
		miaoshaMessage.setGoodsVo(goodsVo);
		miaoshaMessage.setMd5(md5);
		miaoshaMessage.setUser(user);
		
		mqSender.miaoshaSend(miaoshaMessage);//消息入队
	
		
	/*	OrderInfo orderInfo= miaoshaService.miaosha(user, goodsVo, md5);
		modelmap.put("success", true);
		modelmap.put("orderInfo", orderInfo);*/
	
		return 	Result.success(0);

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

	
    /**
     * orderId：成功
     * -1：秒杀失败
     * 0： 排队中
     * */
    @GetMapping(value="/result")
    @ResponseBody
    public Result<Long> miaoshaResult(User user,
    		@RequestParam("goodsId")long goodsId) {
    	
    
    	long result  =miaoshaService.getMiaoshaResult(user.getUserId(), goodsId);
    	return Result.success(result);
    }


}
