/**
 * 文 件 名:  RandomValidateCode.java
 * 描    述:  <生成随机验证码图片>
 * 创 建 人:  pfma
 * 创建时间:  2014年6月10日
 * 修改内容:  <修改内容>
 */
package com.freepaay.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <验证码图片生成> <使用awt生成验证码>
 * 
 * @author 福福
 * @data 2016年6月5日
 */
public class RandomValidateCode {
	/**
	 * 日志对象
	 */
	private static LoggerAdapter log = LoggerAdapterFacory.getLoggerAdapter(RandomValidateCode.class);

	public static final String RANDOMCODEKEY = "validateCode";// 放到session中的key

	private Random random = new Random();

	private String randString = "012356789";// 随机产生的字符串

	private int width = 116;// 图片宽

	private int height = 26;// 图片高

	private int lineSize = 100;// 干扰线数量

	private int stringNum = 6;// 随机产生字符数量

	private String fontFamily = "Verdana";
	
	private int fontSize = 23;

	/*
	 * 获得字体
	 */
	private Font getFont() {
		return new Font(fontFamily, Font.CENTER_BASELINE, fontSize);
	}

	/*
	 * 获得颜色
	 */
	private Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}

	/**
	 * 生成随机图片
	 */
	public void getRandcode(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像的类
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
		g.fillRect(0, 0, width, height);
		g.setFont(new Font(fontFamily, Font.ROMAN_BASELINE, fontSize));
		g.setColor(getRandColor(110, 133));
		// 绘制干扰线
		for (int i = 0; i <= lineSize; i++) {
			drowLine(g);
		}
		// 绘制随机字符
		String randomString = "";
		for (int i = 1; i <= stringNum; i++) {
			randomString = drowString(g, randomString, i);
		}
		session.removeAttribute(RANDOMCODEKEY);
		session.setAttribute(RANDOMCODEKEY, randomString);
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());// 将内存中的图片通过流动形式输出到客户端
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	/*
	 * 绘制字符串
	 */
	private String drowString(Graphics g, String randomString, int i) {
		g.setFont(getFont());
		g.setColor(new Color(random.nextInt(226), random.nextInt(126), random.nextInt(226)));
		String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
		randomString += rand;
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 16);
		return randomString;
	}

	/*
	 * 绘制干扰线
	 */
	private void drowLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(13);
		int yl = random.nextInt(15);
		g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		g.drawLine(x, y, x + xl, y + yl);
	}

	/*
	 * 获取随机的字符
	 */
	public String getRandomString(int num) {
		return String.valueOf(randString.charAt(num));
	}
}
