package com.dzj.miaosha.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dzj.miaosha.entity.MiaoshaOrder;
import com.dzj.miaosha.entity.OrderInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {
	
	@Autowired
	private OrderDao orderDao;
	
	@Test
	public void testInsert() {
		
		OrderInfo orderInfo =new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsPrice(1221.0);
		orderInfo.setUserId(15901870552l);
		orderInfo.setGoodsId(1l);
		orderDao.insertOrderInfo(orderInfo);
		
		MiaoshaOrder miaoshaOrder =new MiaoshaOrder();
		miaoshaOrder.setGoodsId(1l);
		miaoshaOrder.setOrderId(orderInfo.getOrderInfoId());
		miaoshaOrder.setUserId(15901870552l);
		orderDao.insertMiaoshaOrder(miaoshaOrder);
	}

}
