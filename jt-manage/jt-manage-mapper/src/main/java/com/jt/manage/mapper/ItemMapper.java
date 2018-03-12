package com.jt.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.Item;

public interface ItemMapper extends SysMapper<Item>{
	
	public List<Item> findAll();

	public List<Item> findItemList(@Param("begin")int begin,@Param("rows")int rows);
	
	public String findItemName(Long cid);
	
	//批量修改状态
	public void updateStatus(@Param("ids")Long[] ids,@Param("status") int status);
}
