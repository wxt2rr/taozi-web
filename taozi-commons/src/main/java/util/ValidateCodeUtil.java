package util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ValidateCodeUtil {

	private static String RAND_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";// 随机产生的字符串
	private static String RAND_NUMBER = "0123456789";// 随机产生的字符串

	public static String getRandomString(int strLen, String strSet) {
		if (strSet == null || strSet.trim().length() < 1) {
			strSet = RAND_STRING;
		}
		Random random = new Random();
		int n = strSet.length();
		StringBuffer sb = new StringBuffer(strLen);
		for (int i = 0; i < strLen; i++) {
			int idx = random.nextInt(n);
			idx = idx < 0 ? -idx : idx;
			idx = idx % n;
			char c = strSet.charAt(idx);
			sb.append(c);
		}
		return sb.toString();
	}

	public static String getRandomNumber(int strLen, String strSet) {
		if (strSet == null || strSet.trim().length() < 1) {
			strSet = RAND_NUMBER;
		}
		Random random = new Random();
		int n = strSet.length();
		StringBuffer sb = new StringBuffer(strLen);
		for (int i = 0; i < strLen; i++) {
			int idx = random.nextInt(n);
			idx = idx < 0 ? -idx : idx;
			idx = idx % n;
			char c = strSet.charAt(idx);
			sb.append(c);
		}
		return sb.toString();
	}

	public static BufferedImage generateVcodeImage(String vcode, int width, int height, Font font, int lineSize) {
		if (width < 1) {
			width = 80;
		}
		if (height < 1) {
			height = 26;
		}
		if (font == null) {
			font = new Font("Times New Roman", Font.ROMAN_BASELINE, 18);
		}
		Random random = new Random(System.currentTimeMillis());
		// BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
		g.fillRect(0, 0, width, height);
		g.setFont(font);
		g.setColor(getRandColor(random, 110, 133));

		// 绘制随机字符
		drowString(random, g, vcode);

		// 绘制干扰线
		drowLine(random, g, width, height, lineSize);
		g.dispose();
		return image;
	}

	/*
	 * 绘制字符串
	 */
	private static void drowString(Random random, Graphics g, String vcode) {
		g.setFont(getFont());
		for (int i = 0; i < vcode.length(); i++) {
			g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
			g.translate(random.nextInt(3), random.nextInt(3));
			g.drawString(String.valueOf(vcode.charAt(i)), 13 * (i + 1), 16);
		}
	}

	/*
	 * 绘制干扰线
	 */
	private static void drowLine(Random random, Graphics g, int width, int height, int lineSize) {
		for (int i = 0; i <= lineSize; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(50);
			int yl = random.nextInt(60);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// g.drawLine(0, height / 2 - 1 + 5, width, height / 2 - 1 - 5);
		// g.drawLine(0, height / 2 + 5, width, height / 2 - 5);
		// g.drawLine(0, height / 2 + 5 + 1, width, height / 2 + 1 - 5);

	}

	/*
	 * 获得颜色
	 */
	private static Color getRandColor(Random random, int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}

	/*
	 * 获得字体
	 */
	private static Font getFont() {
		return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getRandomString(24, null));
		}
	}
}