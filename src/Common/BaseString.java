package Common;

import java.io.InputStream;

public class BaseString {
	public static InputStream parseStringToIS(String str){
		  if(str==null) return null;
		  //str = str.trim();
		  java.io.InputStream in = null;
		  try{
		   in = new java.io.ByteArrayInputStream(str.getBytes("UTF-8"));
		  }catch(Exception ex){}
		  return in;      
		 }
}
