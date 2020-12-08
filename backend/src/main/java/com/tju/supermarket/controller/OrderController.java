package com.tju.supermarket.controller;

import com.alibaba.fastjson.JSONObject;
import com.tju.supermarket.service.OrderService;
import com.tju.supermarket.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: Controller
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService OrderService;

	/**
	 * 查询列表
	 */
	@RequiresPermissions("Order:list")
	@GetMapping("/listOrder")
	public JSONObject listOrder(HttpServletRequest request) {
		return OrderService.listOrder(CommonUtil.request2Json(request));
	}

	/**
	 * 新增
	 */
	@RequiresPermissions("Order:add")
	@PostMapping("/addOrder")
	public JSONObject addOrder(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "content,goodsName");
		return OrderService.addOrder(requestJson);
	}

	/**
	 * 修改
	 */
	@RequiresPermissions("Order:update")
	@PostMapping("/updateOrder")
	public JSONObject updateOrder(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "id,content,goodsName");
		return OrderService.updateOrder(requestJson);
	}
}
