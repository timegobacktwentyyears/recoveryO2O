package com.jt.manage.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
import com.sun.imageio.plugins.common.ImageUtil;

//表示文件上传的Controller
@Controller
public class UploadController {
	
	/**
	 * 1.获取图片的名称
	 * 2.获取文件的后缀
	 * 3.判断是否为图片类型    gif|jpg|png
	 * 4.判断是否为非法文件
	 * 5.获取宽度和高度
	 * 6.拼接虚拟路径    由浏览器发起访问  url:http://image.jt.com/images/2017/10/31/XXX1.jpg
	 * 7.拼接文件夹路径     D://jt-upload/images/2017/10/31
	 * 8.拼接本地磁盘路径  D://jt-upload/images/2017/10/31/XXX1.jpg
	 * 9.将文件进行写盘操作
	 * @param uploadFile
	 * @return
	 * 页面回显的JSON串: {"error":0,"url":"图片的保存路径","width":图片的宽度,"height":图片的高度}
	 * error 0表示图片    1表示非图片 
	 */
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult uploadFile(MultipartFile uploadFile){
		PicUploadResult result = new PicUploadResult();
		
		//1.获取图片名称         1.jpg
		String fileName = uploadFile.getOriginalFilename();
		
		//2.获取后缀名称        .jpg
		String endType = fileName.substring(fileName.lastIndexOf("."));
		
		//3.判断是否为图片类型   
		if(!endType.matches("^.*(jpg|png|gif)$")){
			result.setError(1); //表示不是一个图片
			return result;
		}
		//说明图片的后缀正确   判断是否为恶意程序
		try {
			BufferedImage imageBuffer = ImageIO.read(uploadFile.getInputStream());
			//获取高度和宽度
			String height = imageBuffer.getHeight() +"";
			String width = imageBuffer.getWidth() + "";
			result.setHeight(height);
			result.setWidth(width);
			
			//定义网络访问的虚拟路径
			String url = "http://image.jt.com/images/";
			
			//定义本地磁盘路径
			String path = "D:/jt-upload/images/";
			
			//为了避免图片名称重复问题   以yyyy/MM/dd/三位随机数+文件名称
			String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			
			//随机数                              100--999
			int randomNumber = new Random().nextInt(900) + 100;
			
			//页面的访问路径          http://image.jt.com/images/2017/10/31/9991.jpg
			String urlPath = url + datePath + "/" +randomNumber + fileName;
			result.setUrl(urlPath);
			

			//创建文件夹                  D:/jt-upload/images/2017/10/31/9991.jpg
			String localDir = path + datePath;
			
			File file = new File(localDir);
			
			//判断文件夹是否存在
			if(!file.exists()){
				file.mkdirs();	//创建文件夹
			}
			
			//D:/jt-upload/images/2017/10/31/9991.jpg     执行写盘操作
			String localPath = localDir +"/" + randomNumber+fileName;
			uploadFile.transferTo(new File(localPath));
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			//证明不是图片
			result.setError(1);
			return result;
		} 
	}
	
	
	
	
}
