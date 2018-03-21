package com.recover.dao;

import com.recover.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author sanyue
 */
@Repository
public interface UserDao {

    User getByuserNameAndPasswordType(@Param("userName") String userName, @Param("password") String password, @Param("type") Integer type);

    User getByName(String userName);

    Integer insert(User user);

    User getOne(Long userId);
}
