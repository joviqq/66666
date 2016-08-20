package com.freepaay.common.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freepaay.exception.FreepaayException;
import com.freepaay.tfs.util.TFSUtil;

@Controller
public class TFSFileController extends BaseController {

	@RequestMapping(value = "/file/upload/{originName}/{fileName}/open")
	public void tfsFileOpenHandler(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@PathVariable String fileName,
			@PathVariable String originName) throws FreepaayException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println(fileName);
		ByteArrayOutputStream fosByte = (ByteArrayOutputStream) TFSUtil.getTfsFile(fileName);
		byte[] bytes = fosByte.toByteArray();
		ByteArrayInputStream fis = new ByteArrayInputStream(bytes);
        byte[] bytesRead = new byte[1024*1024];
        int length = 0;
        while((length=fis.read(bytesRead))!=-1){
            response.getOutputStream().write(bytesRead,0,length);
        }
	}
	
	@RequestMapping(value = "/file/upload/{tfsFileName}/{originName}")
	public void tfsFileHandler(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@PathVariable String tfsFileName,
			@PathVariable String originName) throws FreepaayException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println(tfsFileName);
		ByteArrayOutputStream fosByte = (ByteArrayOutputStream) TFSUtil.getTfsFile(tfsFileName);
		byte[] bytes = fosByte.toByteArray();
		ByteArrayInputStream fis = new ByteArrayInputStream(bytes);
        byte[] bytesRead = new byte[1024*1024];
        int length = 0;
        while((length=fis.read(bytesRead))!=-1){
            response.getOutputStream().write(bytesRead,0,length);
        }
	}
}
