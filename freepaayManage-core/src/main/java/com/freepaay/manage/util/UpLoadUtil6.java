package com.freepaay.manage.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.freepaay.tfs.util.TFSUtil;

public class UpLoadUtil6 {

	/*存储文件返回格式为"/file/upload/"+picture.getOriginalFilename()+"/"+uploadMethod6(picture)+"/open"*/
	public static String uploadMethodWithPath(MultipartFile picture)
			throws IOException {
		/*String dateString = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		indentity=indentity + File.separator
				+ dateString + File.separator + UUID.randomUUID().toString().replaceAll("-", "");*/
		return "/file/upload/"+picture.getOriginalFilename()+"/"+uploadMethod6(picture)+"/open";
	}

	/*存储文件返回格式tfsFileName*/
	public static String uploadMethod6(MultipartFile picture)
			throws IOException {
		String tfsFileName = "";
		String fileName="";
		if (!picture.isEmpty()) {
			/*String path = request.getSession().getServletContext().getRealPath("/") + File.separator + "upload"
					+ File.separator + indentity;*/
			fileName = picture.getOriginalFilename();
			tfsFileName = TFSUtil.saveTfsByteFile(picture.getBytes(), fileName);
			/*pathnew = File.separator + "upload" + File.separator + indentity + File.separator + fileName;
			System.out.println(fileName);
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			picture.transferTo(targetFile);*/
		}
		return tfsFileName;
	}
	
	/*删除文件，格式为/file/upload/"+picture.getOriginalFilename()+"/"+uploadMethod6(picture)+"/open*/
	public static void deleteFileWithPath(String fileName) {
		/*String fileFullPath = request.getSession().getServletContext().getRealPath("/") + filePath;
		(new File(fileFullPath)).delete();
		String folderName = fileFullPath.substring(0, fileFullPath.lastIndexOf(File.separator));
		deleteDir(new File(folderName));*/
		String[] temp = fileName.split("file/upload/");
		String[] temp1 = temp[1].split("/");
		TFSUtil.deleteTfsFile(temp1[1]);
	}
	
	/*删除文件，格式为tfsFileName*/
	public static void deleteFile(String fileName) {
		/*String fileFullPath = request.getSession().getServletContext().getRealPath("/") + filePath;
		(new File(fileFullPath)).delete();
		String folderName = fileFullPath.substring(0, fileFullPath.lastIndexOf(File.separator));
		deleteDir(new File(folderName));*/
		TFSUtil.deleteTfsFile(fileName);
	}

}
