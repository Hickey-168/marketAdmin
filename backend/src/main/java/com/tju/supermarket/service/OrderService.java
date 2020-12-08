package com.tju.supermarket.service;

import com.alibaba.fastjson.JSONObject;

public interface OrderService {
    /**
     * 新增文章
     */
    JSONObject addOrder(JSONObject jsonObject);

    /**
     * 文章列表
     */
    JSONObject listOrder(JSONObject jsonObject);

    /**
     * 更新文章
     */
    JSONObject updateOrder(JSONObject jsonObject);
}



