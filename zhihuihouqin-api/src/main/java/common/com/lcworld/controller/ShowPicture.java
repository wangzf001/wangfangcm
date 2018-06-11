package com.lcworld.controller;

import java.io.*;

import javax.servlet.*;

import javax.servlet.http.*;

import com.sun.image.codec.jpeg.*;//sun公司仅提供了jpg图片文件的编码api

import javax.imageio.stream.*;

import java.awt.*;

import java.awt.image.BufferedImage;

public class ShowPicture  extends HttpServlet{
     private static final String JPG="image/jpeg;charset=GB2312";
     public void init()  throws ServletException{

     }

     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
     }
 
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
       String imagePath="/jpg/登录设计.jpg";//图片相对web应用的位置
        
       OutputStream output = response.getOutputStream();//得到输出流
	   response.setContentType(JPG);//设定输出的类型
      //得到图片的真实路径      
       imagePath = getServletContext().getRealPath(imagePath);
     //得到图片的文件流
      InputStream is = new FileInputStream(new File(imagePath));
     //得到输入的编码器，将文件流进行jpg格式编码
      JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(is);
     //得到编码后的图片对象
     BufferedImage image = decoder.decodeAsBufferedImage();
     //得到输出的编码器
      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
      encoder.encode(image);//对图片进行输出编码
      is.close();//关闭文件流
      output.close();
	 }
}
