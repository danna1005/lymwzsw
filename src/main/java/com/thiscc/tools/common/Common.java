package com.thiscc.tools.common;

import java.io.File;
import java.util.Vector;

public class Common {
	
	public static String newPath(String dir,String name){
		if(dir.endsWith(".db")){
			dir = dir.substring(0, dir.lastIndexOf("/")+1);
		}
		if(!dir.endsWith("/"))
			dir += "/";
		File file = new File(dir);
		if(!file.exists())
			file.mkdir();
		dir += name;
		return dir;
	}
	public static String[] mySplit(String str, String ope) {
		String[] ret = null;
		Vector<String> vec = new Vector<String>(1, 1);
		int index = str.indexOf(ope);
		while (index != -1) {
			vec.addElement(str.substring(0, index));
			str = str.substring(index + ope.length(), str.length());
			index = str.indexOf(ope);
		}
		if (str.length() > 0)
			vec.addElement(str);
		ret = new String[vec.size()];
		for (int i = 0; i < vec.size(); i++) {
			ret[i] = vec.elementAt(i).toString();
		}
		return ret;
	}
	public static boolean sql_inj(String str)  {  
	   String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|+|,";  
	   String inj_stra[] = mySplit(inj_str,"|");  
	   for (int i=0 ; i < inj_stra.length;i++){  
 	       if (str.indexOf(inj_stra[i])>=0){  
		       return true;  
		   }  
	   }  
	   return false;  
	} 
}
