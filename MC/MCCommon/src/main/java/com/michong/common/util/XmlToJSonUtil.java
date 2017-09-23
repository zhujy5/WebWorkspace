package com.michong.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;


public class XmlToJSonUtil {

	@SuppressWarnings("resource")
	public static String Xml2JsonString(String xmlPath) throws IOException{
		//读取src下的xml文件
        File file=new File(xmlPath);
        
        //字符流输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
        String string=null;
        StringBuffer sb = new StringBuffer();
        while((string = reader.readLine())!=null){
            sb.append(string);
        }
        System.out.println(sb.toString());
        XMLSerializer xmlSerializer=new XMLSerializer();
        JSON json=xmlSerializer.read(sb.toString());

        //这一句的输出,也许你很快的就知道原理了,其实原理很简单的！
        System.out.println("重点处：\n"+json.toString(1)+"\n");
        return json.toString(1);
        //截取掉[],转化为JSONObject
        //JSONObject jsonObject=JSONObject.fromObject((json.toString()).substring(1, json.toString().length()-1));
        //System.out.println("截取后：\n"+jsonObject.toString(1)+"\n");

    }
	public static void main(String[] args) throws IOException {
        Xml2JsonString("E:\\test.xml");
	}	
	
	
}
