package com.jt.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

@Table(name="tb_item")
public class Item extends BasePojo{

	@Id		//表示当前属性为数据表中的主键字段
	@GeneratedValue(strategy=GenerationType.IDENTITY) //表示主键自增
	private Long id;		//商品的id
	private String title;	//商品的标题
	private String sellPoint;//商品的买点
	private Integer num;	//商品的数量
	private Long price;		//商品的价格
	private String barcode;	//条形码
	private String image;	//商品的图片的url信息
	private Long Cid;		//通过ajax发起请求，商品分类id号
	private Integer status; //1正常  2下架  3 删除
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
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
		return Cid;
	}
	public void setCid(Long cid) {
		Cid = cid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
