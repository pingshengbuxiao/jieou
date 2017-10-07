package com.xie.jieou.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class WordUtils {
	
	
	public static String getChinese() {  
        String str = null;  
        int highPos, lowPos;  
        Random random = new Random();  
        highPos = (176 + Math.abs(random.nextInt(71)));//区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字  
        random=new Random();  
        lowPos = 161 + Math.abs(random.nextInt(94));//位码，0xA0打头，范围第1~94列  
  
        byte[] bArr = new byte[2];  
        bArr[0] = (new Integer(highPos)).byteValue();  
        bArr[1] = (new Integer(lowPos)).byteValue();  
        try {  
            str = new String(bArr, "GB2312");   //区位码组合成汉字  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
            return str;  
    } 
	
	public static void main(String args[]) {
		System.out.println(WordUtils.getChinese());
	}
}
