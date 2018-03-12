package com.jt.web.service;

import java.util.List;

import com.jt.web.pojo.Item;

public interface SearchService {

	List<Item> findItemListByKey(String keyWord);

}
