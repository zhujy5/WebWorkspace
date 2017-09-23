package com.michong.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.apache.log4j.Logger;

public class HttpRequestUtil {
	private static Logger log = Logger.getLogger(HttpRequestUtil.class);
	//链接超时时间
	private static int CONNECT_TIME_OUT = 60 * 30;
	//请求超时时间
	private static int READ_TIME_OUT = 60;
	
	
	public static void main(String[] args) {
		String url = "https://www.baidu.com";
		String string = sendGet(url,"UTF-8");
		System.out.println(string);
	}
	
	/**
	 * 模拟浏览器发送get请求
	 */
	public static String sendGet(String url, String charset) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			URL realUrl = new URL(url);
			//打开URL之间的链接
			URLConnection conn = realUrl.openConnection();
			//设置通用的请求参数
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setConnectTimeout(CONNECT_TIME_OUT);
			conn.setReadTimeout(READ_TIME_OUT);
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 3.0.04506)");
			//建立实际链接
			conn.connect();
			//定义BufferReader输入流来读取URL的响应
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(),charset));
			String line;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			
		} catch (Exception e) {
			log.error("["+HttpRequestUtil.class.getName()+"]读取信息失败：", e);
		} finally {
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e2) {
				log.error("["+HttpRequestUtil.class.getName()+"]关闭输入流失败：", e2);
			}
		}

		return sb.toString();
	}
	/**
	 * 模拟浏览器发送post请求
	 */
	public static String sendPost(String url,String param,String charset,String token){
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer result  = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			//打开URL之间的链接
			URLConnection conn = realUrl.openConnection();
			//设置通用的请求参数
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setConnectTimeout(CONNECT_TIME_OUT);
			conn.setReadTimeout(READ_TIME_OUT);
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 3.0.04506)");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			conn.setRequestProperty("Authorization", "xxx" + token);
			//发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//获取URLConnection对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			//发送请求参数
			out.print(param);
			//flush输出流的缓冲
			out.flush();
			
			//定义BufferReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),charset));
			String line;
			while((line = in.readLine()) != null){
				result.append(line);
			}
		} catch (Exception e) {
			log.error("["+HttpRequestUtil.class.getName()+"]读取信息失败：", e);
		} finally {
			try {
				if(out != null){
					out.close();
				}
				if(in != null){
					in.close();
				}
			} catch (IOException e2) {
				log.error("["+HttpRequestUtil.class.getName()+"]关闭输入流失败：", e2);
			}
		}
		return result.toString();
	}
}
