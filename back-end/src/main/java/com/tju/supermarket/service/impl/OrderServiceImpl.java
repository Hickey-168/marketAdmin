package com.tju.supermarket.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tju.supermarket.dao.OrderDao;
import com.tju.supermarket.service.OrderService;
import com.tju.supermarket.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao OrderDao;

    /**
     * 新增
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addOrder(JSONObject jsonObject) {
        OrderDao.addOrder(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 列表
     */
    @Override
    public JSONObject listOrder(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = OrderDao.countOrder(jsonObject);
        List<JSONObject> list = OrderDao.listOrder(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     * 更新
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateOrder(JSONObject jsonObject) {
        OrderDao.updateOrder(jsonObject);
        return CommonUtil.successJson();
    }
}
