package com.recover.service;

import com.alibaba.fastjson.JSONObject;
import com.recover.dao.Address;
import com.recover.dao.UserDao;
import com.recover.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sanyue
 */
@Service
public class AddressService {

    @Autowired
    private UserDao userDao;
    /**
     * 获取用户地址（只有一个默认地址）
     * 传参：userId 用户id
     * 返回格式： {"address":"浙江省杭州市西湖区福鼎家园"}
     */
    public JSONObject getAddress(@Param("userId") Long userId){
        User user = userDao.getOne(userId);
        JSONObject json = new JSONObject();
        json.put("address",user.getUserAddress()+user.getFullAddress());
        return json;
    }

}
