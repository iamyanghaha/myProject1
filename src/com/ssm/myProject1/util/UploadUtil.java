package com.ssm.myProject1.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ssm.myProject1.object.InputObject;
import com.ssm.myProject1.object.OutputObject;



public class UploadUtil {
	
	/**
	 * 上传图片
	 * @param inputObject
	 * @param outputObject
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public static Map<String , Object> imgFileUpload(InputObject inputObject,OutputObject outputObject,CommonsMultipartFile files) throws Exception{
		Map<String,Object> map = inputObject.getParams();
		int imgType = Integer.parseInt(map.get("imgType").toString());
		String uoloadPath = "";
		switch (imgType) {
		case 1:
			uoloadPath = "upload\\manager\\";// 管理员图片存放路径
			break;
		case 2:
			uoloadPath = "upload\\hotel\\";// 酒店图片存放路径
			break;
		case 3:
			uoloadPath = "upload\\sarea\\";// 景区图片存放路径
			break;
		case 4:
			uoloadPath = "upload\\information\\";// 资讯图片存放路径
			break;
		case 5:
			uoloadPath = "upload\\opinions\\";// 舆情图片存放路径
			break;
		case 6:
			uoloadPath = "upload\\shop\\";// 商城图片存放路径
			break;
		case 7:
			uoloadPath = "upload\\car\\";// 汽车图片存放路径
			break;
		case 8:
			uoloadPath = "upload\\food\\";// 小吃图片存放路径
			break;
		case 9:
			uoloadPath = "upload\\culture\\";// 文化图片存放路径
			break;
		case 10:
			uoloadPath = "upload\\brand\\";// 品牌图片存放路径
			break;
		case 11:
			uoloadPath = "upload\\video\\";// 视频图片存放路径
			break;
		case 12:
			uoloadPath = "upload\\advertisement\\";// 广告位图片存放路径Picture
			break;
		case 13:
			uoloadPath = "upload\\picture\\";// 广告位图片存放路径
			break;
			default:
				return null;
		}
		String filePath = inputObject.getRequest().getSession().getServletContext().getRealPath("/") + uoloadPath;//拼装上传路径
		String fileName = files.getOriginalFilename();// 文件名称
		//得到文件扩展名
		String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
		boolean a = JudgeUtil.contains(Constants.IMG_TYPE,fileExtName.toLowerCase());
		if(a){
			InputStream ins = files.getInputStream();
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream(); 
			byte[] buff = new byte[100];
			int rc; 
			while ((rc = ins.read(buff, 0, 100)) > 0) { 
				swapStream.write(buff, 0, rc); 
			} 
			byte[] in_b = swapStream.toByteArray();
			if(!isImage(new ByteArrayInputStream(in_b))){
				outputObject.setreturnMessage("文件类型不正确，只允许上传jpg,png,jpeg,svg格式的图片");
				return null;
			}else{
				String uuid = UUID.randomUUID().toString().replace("-", "");// 为了避免文件名重复，在文件名前加UUID加密字符串
				String uuidFileName = uuid + fileName;
				upFile(files.getInputStream(), uuidFileName, filePath);// 将文件保存到服务器
				Map<String,Object> bean = new HashMap<String, Object>();
				
				bean.put("optionPath", uoloadPath + uuidFileName);
				return bean;
			}
		}else{
			outputObject.setreturnMessage("文件类型不正确，只允许上传jpg,png,jpeg,svg格式的图片");
			return null;
		}
	}
	
	/** 
	 * 通过读取文件并获取其width及height的方式，来判断判断当前文件是否图片
	 *  
	 * @param imageFile 
	 * @return 
	 */  
	public static boolean isImage(InputStream ins) {  
		try {
			BufferedImage img =  ImageIO.read(ins);
			if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {  
		    	return false;  
		    }  
		    return true; 
		} catch (IOException e) {
			System.out.println("判断图片格式异常");
		}
		return false;
	}  
	
	/**
	 * 上传附件
	 * @param inputObject
	 * @param outputObject
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public static Map<String , Object> vedioUpload(InputObject inputObject,OutputObject outputObject,CommonsMultipartFile files) throws Exception{
		String fileRealResistPath = "upload\\vedio\\main\\";// 文件存放真实相对路径
		String ffmpegRealResistPath = "upload\\vedio\\ffmpeg\\";// 文件截屏存放真实相对路径
		String ffmpeg = "upload\\util\\ffmpeg.exe";
		String Gfile = inputObject.getRequest().getSession().getServletContext().getRealPath("/");
		String filePath = Gfile + fileRealResistPath;
		String ffmpegPath = Gfile + ffmpegRealResistPath;//截屏保存路径
		String ffmpegGPath = Gfile + ffmpeg;//工具路径
		
		String fileName = files.getOriginalFilename();// 文件名称
		
		// 为了避免文件名重复，在文件名前加UUID加密字符串
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String uuidFileName = uuid + fileName;
		String uuidffmpegPath = uuid + ".jpg";
		// 将文件保存到服务器
		upFile(files.getInputStream(), uuidFileName, filePath);
		
		Map<String,Object> map = new HashMap<String, Object>();
		if(fileName.substring(fileName.indexOf(".")+1,fileName.length()).equalsIgnoreCase("mp4")
				||fileName.substring(fileName.indexOf(".")+1,fileName.length()).equalsIgnoreCase("rm")
				||fileName.substring(fileName.indexOf(".")+1,fileName.length()).equalsIgnoreCase("rmvb")
				||fileName.substring(fileName.indexOf(".")+1,fileName.length()).equalsIgnoreCase("wmv")
				||fileName.substring(fileName.indexOf(".")+1,fileName.length()).equalsIgnoreCase("avi")
				||fileName.substring(fileName.indexOf(".")+1,fileName.length()).equalsIgnoreCase("3gp")
				||fileName.substring(fileName.indexOf(".")+1,fileName.length()).equalsIgnoreCase("mkv")){
			if(take(filePath+uuidFileName,ffmpegPath+uuidffmpegPath,ffmpegGPath)){
				map.put("optionPicPath", ffmpegRealResistPath + uuidffmpegPath);//附件截屏路径
			}else{
				map.put("optionPicPath", "");//附件截屏路径
			}
		}else{
			map.put("optionPicPath", fileRealResistPath+uuidFileName);
		}
		map.put("optionPath", fileRealResistPath+uuidFileName);//附件路径
		map.put("optionName", fileName.substring(0,fileName.indexOf(".")));//附件原始名称
		map.put("optionType", fileName.substring(fileName.indexOf(".")+1,fileName.length()));//附件类型
		map.put("optionSize", TransUtil.FormetFileSize(files.getSize()));//附件大小
		map.put("optionSizeUnit", TransUtil.FormetFileUnit(files.getSize()));//附件大小单位B，KB。。。
		return map;
	}
	
	/**
	 * 获取视频截图
	 * @param videoLocation
	 * @param imageLocation
	 * @return
	 */
	public static boolean take(String videoLocation, String imageLocation, String ffmpegGPath){
		// 低精度
		List<String> commend = new ArrayList<String>();
		commend.add(ffmpegGPath);//视频提取工具的位置
		commend.add("-i");
		commend.add(videoLocation);
		commend.add("-y");
		commend.add("-f");
		commend.add("image2");
		commend.add("-ss");
		commend.add("08.010");
		commend.add("-t");
		commend.add("0.001");
		commend.add("-s");
		commend.add("352x240");
		commend.add(imageLocation);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.start();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 单个文件上传
	 * @param is
	 * @param fileName
	 * @param filePath
	 */
	public static void upFile(InputStream is, String fileName, String filePath) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		File f = new File(filePath + "/" + fileName);
		try {
			bis = new BufferedInputStream(is);
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);
			byte[] bt = new byte[4096];
			int len;
			while ((len = bis.read(bt)) > 0) {
				bos.write(bt, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bos) {
					bos.close();
					bos = null;
				}
				if (null != fos) {
					fos.close();
					fos = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
				if (null != bis) {
					bis.close();
					bis = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
