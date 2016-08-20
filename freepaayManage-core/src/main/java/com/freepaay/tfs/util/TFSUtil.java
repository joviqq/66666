package com.freepaay.tfs.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.util.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.common.tfs.TfsManager;
import com.taobao.common.tfs.packet.FileInfo;

public class TFSUtil {

	static ApplicationContext context = new ClassPathXmlApplicationContext("config_spring/TFS.xml");

	private static TfsManager getTfSManager() {
		return (TfsManager) context.getBean("tfsManager");
	}

	private static String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/*存储文件后返回在TFS中存储的文件码*/
	public static String saveTfsByteFile(byte[] fileBytes, String fileName) {
		TfsManager tfsManager = getTfSManager();
		String fileExt = getFileExt(fileName);
		String tfsfileName = tfsManager.saveFile(fileBytes, null, fileExt, true);
		return tfsfileName;
	}
	
	/*存储文件后返回在TFS中存储的文件码*/
	public static String saveTfsFile(InputStream inputStream, String fileName) {
		try {
			TfsManager tfsManager = getTfSManager();
			byte[] fileBytes = IOUtils.toByteArray(inputStream);
			String fileExt = getFileExt(fileName);
			String tfsfileName = tfsManager.saveFile(fileBytes, null, fileExt, true);
			return tfsfileName;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static OutputStream getTfsFile(String tfsFileName) {
		TfsManager tfsManager = getTfSManager();
		OutputStream outputStream = new ByteArrayOutputStream();
		boolean result = tfsManager.fetchFile(tfsFileName, "", outputStream);
		if (result) {
			return outputStream;
		} else {
			return null;
		}
	}

	public static boolean deleteTfsFile(String tfsFileName) {
		TfsManager tfsManager = getTfSManager();
		boolean flag = tfsManager.unlinkFile(tfsFileName, null);
		return flag;
	}

	public void fetchTfsFile(String tfsFileName, String newFileName) {
		TfsManager tfsManager = getTfSManager();
		boolean flag = tfsManager.fetchFile(tfsFileName, null, newFileName);
		System.out.println(flag);
	}

	public void hideTfsFile(String tfsFileName, int isHidden) {
		TfsManager tfsManager = getTfSManager();
		boolean flag = tfsManager.hideFile(tfsFileName, null, isHidden);
		System.out.println(flag);
	}

	public FileInfo stateTfsFile(String tfsFileName) {
		TfsManager tfsManager = getTfSManager();
		FileInfo fileInfo = tfsManager.statFile(tfsFileName, null);
		return fileInfo;
	}

}
