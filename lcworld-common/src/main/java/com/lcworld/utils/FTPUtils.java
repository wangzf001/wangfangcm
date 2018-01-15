package com.lcworld.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


public class FTPUtils {
	public static  String FTP_BASE_URL ;
    static FTPUtils util = new FTPUtils();
	public static final String ANONYMOUS_USER="anonymous";
    private static Properties properties;
	private  String host;
	private  int port;
	private  String user;
	private  String password;
	private  String ftpPath;
	
	static {
	    properties = new Properties();
        try {
            InputStream inputStream = FTPUtils.class.getClassLoader().getResourceAsStream(
                    "ftpconfig.properties");
            properties.load(inputStream);
            FTP_BASE_URL = properties.getProperty("host")+"/";
        } catch (IOException e1) {
            e1.printStackTrace();
        }
	}
	
	public  static FTPUtils getInstances(String ftpPath){
		getInstances();
		util.ftpPath =ftpPath;
	    return util;
	}
	
	public  static FTPUtils getInstances(){
		util.host = properties.getProperty("host");
		util.port = Integer.parseInt(properties.getProperty("port", "21"));
		util.user = properties.getProperty("user");
		util.password = properties.getProperty("password");
		util.ftpPath = properties.getProperty("ftpPath");
		return util;
	}
	
	
	public FTPUtils(){
	    
	}
	
	public FTPUtils(String host, int port, String user, String password, String ftpPath) {
		this.host = host;
		this.user = user;
		this.password = password;
		this.ftpPath = ftpPath;
		this.port = port;
	}
	/**
	     * 连接FTP Server
	     * @throws IOException
	     */
		private FTPClient connect(){        
	        FTPClient client = new FTPClient();
	        try{
	            //连接FTP Server
	            client.connect(this.host, this.port);
	            //登陆
	            if(this.user==null||"".equals(this.user)){
	                //使用匿名登陆
	                client.login(ANONYMOUS_USER, ANONYMOUS_USER);
	            }else{
	                client.login(this.user, this.password);
	            }
	            //设置文件格式
	            client.setFileType(FTPClient.BINARY_FILE_TYPE);
	            //获取FTP Server 应答
	            int reply = client.getReplyCode();
	            if(!FTPReply.isPositiveCompletion(reply)){
	                client.disconnect();
	                return null;
	            }
	            //切换工作目录
	            changeWorkingDirectory(client);
	            System.out.println("===连接到FTP："+host+":"+port);
	        }catch(IOException e){
	            return null;
	        }
	        return client;
	    }
	    /**
	     * 切换工作目录，远程目录不存在时，创建目录
	     * @param client
	     * @throws IOException
	     */
	    private void changeWorkingDirectory(FTPClient client) throws IOException{
	        if(this.ftpPath!=null&&!"".equals(this.ftpPath)){
	            boolean ok = client.changeWorkingDirectory( this.ftpPath);
	            if(!ok){
	                //ftpPath 不存在，手动创建目录
	                StringTokenizer token = new StringTokenizer(this.ftpPath,"\\//");
	                while(token.hasMoreTokens()){
	                    String path = token.nextToken();
	                    client.makeDirectory(path);
	                    client.changeWorkingDirectory(path);
	                }
	            }
	        }
	    }
	    /**
	     * 断开FTP连接
	     * @param ftpClient
	     * @throws IOException
	     */
	    public void close(FTPClient ftpClient) throws IOException{
	        if(ftpClient!=null && ftpClient.isConnected()){
	            ftpClient.logout();
	            ftpClient.disconnect();
	        }
	        System.out.println("!!!断开FTP连接："+host+":"+port);
	    }
	    
	    /**
	     * 上传文件
	     * @param targetName 上传到ftp文件名
	     * @param localFile 本地文件路径
	     * @return
	     */
	    public boolean upload(String targetName,InputStream fis){
	        //连接ftp server
	        FTPClient ftpClient = connect();

	        if(ftpClient==null){
	            System.out.println("连接FTP服务器["+host+":"+port+"]失败！");
	            return false;
	        }
	        //设置上传后文件名
			System.out.println("enter");
	        try{
	        	System.out.println("文件名====="+targetName);
	            long now = System.currentTimeMillis();
	            //开始上传文件
	            boolean ok = ftpClient.storeFile(targetName, fis);
	            if(ok){//上传成功
	                long times = System.currentTimeMillis() - now;
	            }else {
	            	return false;
	            }
	        }catch(IOException e){
	        	System.out.println("exception : ====="+e);
	            return false;
	        }finally{
				System.out.println("finally"+ fis);
	            try{
	                if(fis!=null)
	                    fis.close();
	                close(ftpClient);
	            }catch(Exception e){}
	        }
	        return true;
	    }
	    
	    /**
	     * 下载文件
	     * @param localPath 本地存放路径
	     * @return
	     */
	    public int download(String localPath){
	        //  连接ftp server
	        FTPClient ftpClient = connect();
	        if(ftpClient==null){
	            System.out.println("连接FTP服务器["+host+":"+port+"]失败！");
	            return 0;
	        }

	        File dir = new File(localPath);
	        if(!dir.exists())
	            dir.mkdirs();
	        FTPFile[] ftpFiles = null;
	        try{
	            ftpFiles = ftpClient.listFiles();
	            if(ftpFiles==null||ftpFiles.length==0)
	                return 0;
	        }catch(IOException e){
	            return 0;
	        }
	        int c = 0;
	        for(int i=0;i<ftpFiles.length;i++){
	            FileOutputStream fos = null;
	            try{
	                String name = ftpFiles[i].getName();
	                fos = new FileOutputStream(new File(dir.getAbsolutePath()+File.separator+name));
	                System.out.println("<<<开始下载文件："+name);

	                long now = System.currentTimeMillis();
	                boolean ok = ftpClient.retrieveFile(new String(name.getBytes("UTF-8"),"ISO-8859-1"), fos);
	                if(ok){//下载成功
	                    long times = System.currentTimeMillis() - now;
	                    System.out.println(String.format("<<<下载成功：大小：%s,上传时间：%d秒", formatSize(ftpFiles[i].getSize()),times/1000));
	                    c++;
	                }else{
	                    System.out.println("<<<下载失败");
	                }
	            }catch(IOException e){
	                System.err.println("<<<下载失败");
	                e.printStackTrace();
	            }finally{
	                try{
	                    if(fos!=null)
	                        fos.close();
	                    close(ftpClient);
	                }catch(Exception e){}
	            }
	        }
	        return c;
	    }
	    
	    private static final DecimalFormat DF = new DecimalFormat("#.##");
	    /**
	     * 格式化文件大小（B，KB，MB，GB）
	     * @param size
	     * @return
	     */
	    private String formatSize(long size){
	        if(size<1024){
	            return size + " B";
	        }else if(size<1024*1024){
	            return size/1024 + " KB";
	        }else if(size<1024*1024*1024){
	            return (size/(1024*1024)) + " MB";
	        }else{
	            double gb = size/(1024*1024*1024);
	            return DF.format(gb)+" GB";
	        }
	    }
	    
	    
	    
	    public static void main(String args[]) throws FileNotFoundException{
//	        FTPUtils ftp = new FTPUtils("123.57.80.58",21,"default","TLcuixpR3yJW","/temp/2016/12");
	        FTPUtils ftp = FTPUtils.getInstances();
	        File file = new File("D:/1.jpg");
	        FileInputStream fis = new FileInputStream(file);
	        ftp.upload("newFile.rar",fis);
	        System.out.println("");
	        //ftp.download("D:/ftp/");
	    }
}