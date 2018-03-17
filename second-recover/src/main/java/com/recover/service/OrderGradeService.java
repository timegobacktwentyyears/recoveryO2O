package com.recover.service;

import com.recover.enums.UserReview;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderGradeService {
    /**
     * 获取固定的用户评价
     */
    public List<UserReview> originReview(){
        List<UserReview> userReviews = Arrays.asList(UserReview.values());
        return userReviews;
    }
    //TODO 用户评价
}
