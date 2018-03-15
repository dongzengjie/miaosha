package com.dzj.miaosha.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dzj.miaosha.vo.GoodsVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsDaoTest {

	@Autowired
	private GoodsDao goodsDao;
	
	@Test
	public void testquerylist() {
		
		List<GoodsVo> list = goodsDao.getListGoodsVo();
		for (GoodsVo goodsVo : list) {
			System.out.println(goodsVo);
		}
		
	}
	
	
	@Test
	public void testquerybyid() {
		
		GoodsVo goodsVo =goodsDao.getGoodsVoById(1L);
		System.out.println(goodsVo);
		
	}
	
	@Test
	public void reduce() {
		
		int effect =goodsDao.reduceStock(2L, new Date());
		System.out.println(effect);
		
	}
	
}
