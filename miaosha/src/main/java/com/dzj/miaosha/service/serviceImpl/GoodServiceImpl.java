package com.dzj.miaosha.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzj.miaosha.dao.GoodsDao;
import com.dzj.miaosha.emuns.GlobalEnums;
import com.dzj.miaosha.exception.MiaoshaException;
import com.dzj.miaosha.redis.GoodsKey;
import com.dzj.miaosha.redis.RedisService;
import com.dzj.miaosha.service.GoodsService;
import com.dzj.miaosha.vo.GoodsVo;

@Service
public class GoodServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private RedisService redisService;

	public List<GoodsVo> getGoodsList() {
		
		//List<GoodsVo> goodsVos =goodsDao.getListGoodsVo();
	
		List<GoodsVo> goodsVos =redisService.getList(GoodsKey.getGoodsList, "", GoodsVo.class);
		if(goodsVos ==null) {
			goodsVos =goodsDao.getListGoodsVo();
			redisService.setList(GoodsKey.getGoodsList, "", goodsVos);
		}
		return goodsVos;
	}

	@Override
	public GoodsVo getGoodsByGoodsId(long goodsId) throws MiaoshaException {
		if (goodsId <= 0) {
			throw new MiaoshaException("商品id为空", GlobalEnums.Server_ERROR.getState());
		}

		GoodsVo goods = null;
		goods = redisService.get(GoodsKey.getById, goodsId + "", GoodsVo.class);
		if (goods == null) {
			goods = goodsDao.getGoodsVoById(goodsId);
			redisService.set(GoodsKey.getById, goodsId + "", goods);
		}

		return goods;
	}

	@Override
	public int reduceStock(long goodsId) {
		if (goodsId <= 0) {
			throw new MiaoshaException("商品id为空", GlobalEnums.Server_ERROR.getState());
		}

		return goodsDao.reduceStock(goodsId, new Date());
	}

}
