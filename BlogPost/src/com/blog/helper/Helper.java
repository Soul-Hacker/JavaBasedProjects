package com.blog.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Helper {
	public static boolean deleteFile(String Path)
	{
		boolean f=false;
		try
		{
			File file=new File(Path);
			f=file.delete();
			
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return f;
		
	}
	public static boolean saveFile(InputStream is,String path)
	{
		boolean f=false;
		try
		{
			byte b[]=new byte[is.available()];
			is.read();
			FileOutputStream fos=new FileOutputStream(path);
			fos.write(b);
			fos.flush();
			fos.close();
			f=true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return f;
		
	}

}
