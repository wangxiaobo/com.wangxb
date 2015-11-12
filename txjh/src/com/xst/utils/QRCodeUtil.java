package com.xst.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * 
 * @author <a href="http://www.xdemo.org/">http://www.xdemo.org/</a> 252878950@qq.com
 */
public class QRCodeUtil {
	
	private static final String FORMAT="PNG";
	
	/**
	 * 生成二维码
	 * @param contents 内容，换行可以用\n
	 * @param dest 生成二维码图片地址
	 * @param width 宽度
	 * @param height 高度
	 * @throws WriterException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void encode(String contents,String dest,int width,int height) throws WriterException, FileNotFoundException, IOException{
		contents=new String(contents.getBytes("UTF-8"),"ISO-8859-1");
		QRCodeWriter writer=new QRCodeWriter();
		BitMatrix matrix=writer.encode(contents, BarcodeFormat.QR_CODE, width, height);
		//MatrixToImageWriter.writeToFile(matrix, format, new File(dest));//过时方法不推荐
		MatrixToImageWriter.writeToStream(matrix, FORMAT, new FileOutputStream(new File(dest)));
	}
	
	public static ByteArrayOutputStream  encodeByte(String contents,int width,int height) throws WriterException, FileNotFoundException, IOException{
		contents=new String(contents.getBytes("UTF-8"),"ISO-8859-1");
		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		QRCodeWriter writer=new QRCodeWriter();
		BitMatrix matrix=writer.encode(contents, BarcodeFormat.QR_CODE, width, height);
		//MatrixToImageWriter.writeToFile(matrix, format, new File(dest));//过时方法不推荐
		MatrixToImageWriter.writeToStream(matrix, FORMAT, bout);
		return bout;
	}
	
	
	/**
	 * 从一张图片解析出二维码信息
	 * @param dest 目标地址
	 * @return String 二维码信息
	 * @throws IOException
	 * @throws NotFoundException
	 * @throws ChecksumException
	 * @throws FormatException
	 */
	public static String decode(String dest) throws IOException, NotFoundException, ChecksumException, FormatException{
		QRCodeReader reader=new QRCodeReader();
		BufferedImage image=ImageIO.read(new File(dest));
		LuminanceSource source=new BufferedImageLuminanceSource(image);
		Binarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap imageBinaryBitmap = new BinaryBitmap(binarizer  );  
		Result result = reader.decode(imageBinaryBitmap);
//		System.out.println("result = "+ result.toString());  
//      System.out.println("resultFormat = "+ result.getBarcodeFormat());  
//      System.out.println("resultText = "+ result.getText());
        return result.getText();
	}
	
	public static void main(String[] args) throws WriterException, IOException, NotFoundException, ChecksumException, FormatException {
		String content = "BEGIN:VCARD\n" +
			    "VERSION:3.0\n" +
			    "N:李德伟\n" +
			    "EMAIL:1606841559@qq.com\n" +
			    "TEL:12345678912\n" +
			    "CELL:12345678912\n" +
			    "ADR:山东济南齐鲁软件园\n" +
			    "ORG:济南\n" +
			    "TITLE:软件工程师\n" +
			    "URL:http://blog.csdn.net/lidew521\n" +
			    "NOTE:呼呼测试下吧。。。\n" +
			    "END:VCARD";
		QRCodeUtil.encode(content, "Target.PNG", 200, 200);
		//createQRCode("http://127.0.0.1:7070/barcode4j/gensvg?msg=111111&type=QR","11.jpg");
		//System.out.println(QRCode.decode("mmqrcode1446639827570.png"));
	}
	
	 public static  void createQRCode( String imgPath,String logPath) throws IOException {  
		 BufferedImage image = ImageIO.read(new File(imgPath));
		 Graphics2D gs = image.createGraphics();
		 
         Image logo = ImageIO.read(new File(logPath));//实例化一个Image对象。
         int widthLogo = logo.getWidth(null)>image.getWidth()*2/10?(image.getWidth()*2/10):logo.getWidth(null), 
        		heightLogo = logo.getHeight(null)>image.getHeight()*2/10?(image.getHeight()*2/10):logo.getWidth(null);
		 /**
	       * logo放在中心
          */
         int x= (image.getWidth() - widthLogo) / 2;
         int y = (image.getHeight() - heightLogo) / 2;
         gs.drawImage(logo, x, y, widthLogo, heightLogo, null);
         gs.dispose();  
         image.flush();
		 ImageIO.write(image, "jpeg", new File("newPic.jpg"));
		 
	 }  
	
	
	

}
