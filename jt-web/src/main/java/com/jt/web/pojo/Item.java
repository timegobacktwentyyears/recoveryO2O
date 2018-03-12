package com.jt.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jt.common.po.BasePojo;

//商品信息
@JsonIgnoreProperties //表示忽略未知字段
public class Item extends BasePojo{
	private Long id;  //商品的编号
	private String title; //商品的标题
	private String sellPoint;  //商品的卖点
	private Long price;			//商品的价格
	private Integer num;		//商品数量
	private String barcode;		//条形码
	private String image;		//商品图片url信息
	private Long cid;			//通过ajax发起请求  商品分类id号
	private Integer status;		//1正常，2下架，3删除'
	private String[] images;    //为了实现前台图片回显添加的属性
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	//每一个的图片信息
	public String[] getImages(){
		if(image !=null){
			return image.split(",");
		}else{
			return null;
		}
	}
	
}
