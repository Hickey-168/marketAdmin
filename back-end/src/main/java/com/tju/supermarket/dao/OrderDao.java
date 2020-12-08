package com.tju.supermarket.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @description: Dao层
 */
public interface OrderDao {
	/**
	 * 新增订单
	 */
	int addOrder(JSONObject jsonObject);

	/**
	 * 统计订单总数
	 */
	int countOrder(JSONObject jsonObject);

	/**
	 *订单列表
	 */
	List<JSONObject> listOrder(JSONObject jsonObject);

	/**
	 * 更新订单
	 */
	int updateOrder(JSONObject jsonObject);
}
