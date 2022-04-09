package com.example.demo.utils;

import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class FileUploadService {

	// 配置文件中指定的文件存放路径
	@Value("${file.upload.path}")
	private String fileUploadPath;

	// 旧项目固定的路径
	private String basePath = "/ZJJG/ZJJG_ZJXDWJ/zjjgpt";

	/**
	 * 文件上传
	 */
	public String upload(MultipartFile file) throws IOException {
		// 截取文件名称
		String fileName = file.getOriginalFilename();
		if (fileName.indexOf("\\") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("\\"));
		}

		// 在文件路径前面加上当前时间-防止文件重名
		SimpleDateFormat sf = new SimpleDateFormat("/yyyy/MM/dd/HH/mm/ss/");
		String timeStr = sf.format(new Date());

		// 创建文件保存目录
		String filePath = basePath + timeStr;
		File targetFile = new File(fileUploadPath + filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存文件至硬盘
		filePath = filePath + fileName;
		FileOutputStream out = null;
		out = new FileOutputStream(fileUploadPath + filePath);
		out.write(file.getBytes());
		out.flush();
		out.close();
		return filePath;
	}

	/**
	 * 文件下载
	 */
	public void download(String path, HttpServletResponse response) throws IOException {
		path = fileUploadPath + path;
		InputStream inputStream = new FileInputStream(path);// 文件的存放路径
		response.reset();
		response.setContentType("application/octet-stream");
		String filename = new File(path).getName();
		response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
		ServletOutputStream outputStream = response.getOutputStream();
		byte[] b = new byte[1024];
		int len;
		// 从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
		while ((len = inputStream.read(b)) > 0) {
			outputStream.write(b, 0, len);
		}
		inputStream.close();
	}

	/**
	 * 文件导出-简单的通用导出
	 */
	public <T>  void excelDownload(List<T> list, Class<T> clazz, HttpServletResponse response) throws IOException {
		// 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String sDate = df.format(new Date());

		// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
		String fileName = URLEncoder.encode("数据导出"+sDate, "UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
//		EasyExcel.write(response.getOutputStream(), clazz).sheet("sheet1").doWrite(list);
		EasyExcel.write("d:\\12.xlsx", clazz).sheet("sheet1").doWrite(list);
	}
}
