package com.dzj.miaosha.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzj.miaosha.dao.GoodsDao;
import com.dzj.miaosha.emuns.GlobalEnums;
import com.dzj.miaosha.exception.MiaoshaException;
import com.dzj.miaosha.service.GoodsService;
import com.dzj.miaosha.vo.GoodsVo;

@Service
public class GoodServiceImpl implements GoodsService {


	@Autowired
	private GoodsDao goodsDao;
	
	public List<GoodsVo> getGoodsList() {
		
		return goodsDao.getListGoodsVo();
	}

	@Override
	public GoodsVo getGoodsByGoodsId(long goodsId) throws MiaoshaException{
		if(goodsId <=0) {
			throw new MiaoshaException("商品id为空",GlobalEnums.Server_ERROR.getState());
		}
		return goodsDao.getGoodsVoById(goodsId);
	}

	@Override
	public int reduceStock(long goodsId) {
		if(goodsId <=0) {
			throw new MiaoshaException("商品id为空",GlobalEnums.Server_ERROR.getState());
		}
		
		
		return goodsDao.reduceStock(goodsId, new Date());
	}

}
