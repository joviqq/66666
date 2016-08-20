package com.freepaay.manage.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class UpLoadUtil {
	
	public static String uploadMethod(MultipartFile picture, String indetity) throws IOException {
		return UpLoadUtil6.uploadMethodWithPath(picture);
		/*String pathnew ="";
		if (!picture.isEmpty()) {
//			String path = WebConfig.get("filePath") + File.separator
//					+ "activity" + File.separator + "upload";
			String path =WebConfig.get("filePath")+ File.separator
					+ "upload"+ File.separator+indetity  ;
			String fileName = UUID.randomUUID().toString().replaceAll("-", "")
					+ picture.getOriginalFilename().substring(
							picture.getOriginalFilename()
									.lastIndexOf("."));
			pathnew =File.separator + "upload"+ File.separator+indetity  + File.separator+fileName ;
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			picture.transferTo(targetFile);
//			org.setIdentityCard1(targetFile.getPath());
			
          
		}
		 return pathnew;*/
	}

}
