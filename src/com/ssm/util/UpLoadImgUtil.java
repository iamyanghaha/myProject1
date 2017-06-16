package com.ssm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ssm.myProject1.object.InputObject;
import com.ssm.myProject1.object.OutputObject;
import com.ssm.myProject1.util.Constants;

/*
* Module ID:               20170102    
* Comments:                上传图片工具类                                        
* JDK version used:        JDK1.7                                                        
* Author：        	           wtk               
* Create Date：                                     创建日期，2017-06-01
* Modified By：  	          <修改人拼音缩写>                                         
* Modified Date:  		  <修改日期，格式:YYYY-MM-DD>                                    
* Why & What is modified：  <修改原因描述>    
* Version:                v_1.0                    
*/

public class UpLoadImgUtil {

	/**
	 * MultipartFile转File
	 * 
	 * @param multfile
	 * @return
	 * @throws IOException
	 */
	public static File MultipartFileToFile(MultipartFile multfile) throws Exception {
		CommonsMultipartFile cf = (CommonsMultipartFile) multfile;
		// 这个myfile是MultipartFile的
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File file = fi.getStoreLocation();
		// 手动创建临时文件
		if (file.length() < 1024) {
			File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + file.getName());
			multfile.transferTo(tmpFile);
			return tmpFile;
		}
		return file;
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param file_img
	 * @return
	 * @throws IOException
	 */
	public static String UploadImg(InputObject inputObject,OutputObject outputObject,CommonsMultipartFile files) throws Exception {
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
			String uuid = UUID.randomUUID().toString().replace("-", "");// 为了避免文件名重复，在文件名前加UUID加密字符串
			String uuidFileName = uuid + fileName.substring(fileName.lastIndexOf("."));
			upFile(files.getInputStream(), uuidFileName, filePath);// 将文件保存到服务器
			/*return "..\\..\\upload\\"+uuidFileName;*/
			System.out.println(uoloadPath+uuidFileName);
			return uoloadPath+uuidFileName;
		}
		return "-1";
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

	/**
	 * 删除图片
	 * 
	 * @param request
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static boolean DeleteImg(HttpServletRequest request, String path_img) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("/") + path_img;
		if (new File(path).delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 上传Excel表
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static boolean UploadExcel(HttpServletRequest request) throws Exception {
		MultipartFile file = (MultipartFile) ((MultipartHttpServletRequest) request).getFile("file");
		String f = "upload/";
		// 产生随机数开始(产生12为随机数作为图片的new name)
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat( "yyMMddHHmmss");
		String n = sdf.format(new java.util.Date());
		String path = request.getSession().getServletContext().getRealPath(f);
		InputStream is = file.getInputStream();
		byte b[] = new byte[is.available()];
		OutputStream out;
		// 图片保存路径(电脑相对路径),路径取到该项目的根目录
		out = new FileOutputStream(path + "/" + n + ".xlsx");
		out.write(b);
		out.close();
		is.close();
		return true;
	}

}
