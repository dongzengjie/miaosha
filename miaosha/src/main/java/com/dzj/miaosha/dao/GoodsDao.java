package com.dzj.miaosha.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dzj.miaosha.vo.GoodsVo;

@Mapper
public interface GoodsDao {
	
	/**
	 * 获取所有秒杀商品
	 * @return
	 */
	public List<GoodsVo> getListGoodsVo();
	
	/**
	 * 根据商品id获取秒杀商品
	 * @param goodsId
	 * @return
	 */
	public GoodsVo getGoodsVoById(Long goodsId);
	
	/**
	 * 减库存
	 * @param goodsId
	 * @param killTime
	 * @return
	 */
	public int reduceStock(@Param("goodsId")Long goodsId,@Param("killTime") Date killTime);
}
