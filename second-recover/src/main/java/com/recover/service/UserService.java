package com.recover.service;

import com.recover.enums.ApiCode;
import com.recover.dao.UserDao;
import com.recover.entity.User;
import com.recover.exception.MineException;
import com.recover.util.ApiResult;
import com.recover.util.sms.MD5;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author sanyue
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public ApiResult signON(@Param("userName") String userName, @Param("password") String password) {
        User user = userDao.getByuserNameAndPasswordType(userName, password, null);
        if (user == null) {
            return new ApiResult(ApiCode.FAIL.getCode(), "该用户不存在，请先注册");
        } else {
            return new ApiResult(ApiCode.SUCCESS.getCode(), "欢迎");
        }
    }

    public ApiResult regist(User user) {
        checkNull(user);

        User userByName = userDao.getByName(user.getUserName());
        if (userByName != null) {
            return new ApiResult(ApiCode.FAIL.getCode(), "名称已重复");
        }

        user.setUserPassword(MD5.MD5Encode(user.getUserPassword()));
        userDao.insert(user);
        return new ApiResult(ApiCode.SUCCESS.getCode(),"注册成功！！！");
    }

    private void checkNull(User user) {
        java.util.List<Object> paramList = new ArrayList<>();
        paramList.add(user.getPhone());
        paramList.add(user.getStatus());
        //paramList.add(user.getUserArea());
        paramList.add(user.getUserDesc());
        paramList.add(user.getUserName());
        paramList.add(user.getUserPassword());
        paramList.add(user.getUserType());
        paramList.stream().forEach(param -> {
            if (param == null){
                throwException(param.toString());
            }
        });
    }

    private void throwException(String thing) {
        throw new MineException(thing + "不能为空");
    }
}

