package com.dzj.miaosha.service.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzj.miaosha.dao.OrderDao;
import com.dzj.miaosha.emuns.GlobalEnums;
import com.dzj.miaosha.entity.MiaoshaOrder;
import com.dzj.miaosha.entity.OrderInfo;
import com.dzj.miaosha.entity.User;
import com.dzj.miaosha.exception.MiaoshaException;
import com.dzj.miaosha.service.OrderService;
import com.dzj.miaosha.vo.GoodsVo;
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	public MiaoshaOrder getMiaoshaOrderByUserIdAndGoodsId(long userId, long goodsId) throws MiaoshaException {
		if(userId<=0 || goodsId <=0) {
			throw new MiaoshaException("参数错误", GlobalEnums.Server_ERROR.getState());
		}
		
		MiaoshaOrder miaoshaOrder =orderDao.getMiaoshaOrderByUserIdAndGoodsId(userId, goodsId);
		if(miaoshaOrder != null) {
			throw new MiaoshaException("重复秒杀", GlobalEnums.Server_ERROR.getState());
		}else {
			return miaoshaOrder;
		}
		
	}

	@Override
	public OrderInfo createOrder(User user, GoodsVo goodsVo) {
		
		OrderInfo orderInfo =new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0l);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goodsVo.getGoodsId());
		orderInfo.setGoodsName(goodsVo.getGoodsName());
		orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
		orderInfo.setUserId(user.getUserId());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);
		
		orderDao.insertOrderInfo(orderInfo);
		MiaoshaOrder miaoshaOrder =new MiaoshaOrder();
		miaoshaOrder.setOrderId(orderInfo.getOrderInfoId());
		miaoshaOrder.setGoodsId(goodsVo.getGoodsId());
		miaoshaOrder.setUserId(user.getUserId());
		orderDao.insertMiaoshaOrder(miaoshaOrder);
		
		return orderInfo;
	}

}
