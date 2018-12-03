package kr.or.ddit.web.model2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileList {
	public List<File> getFileList(String fileNmae) {
		File folder= new File(fileNmae);
		
		File[] folderList=folder.listFiles();
		List<File> filist=new ArrayList<>();
		if(!folder.getName().equals("webStudy01")) {
			filist.add(folder.getParentFile());
		}
		if (folder.isDirectory()) {
			for(File tmp:folderList) {
				filist.add(tmp);
			}
			
		}
		
		return filist;
		}
	public void delFile(File file) {
		List<File> filist=new ArrayList<>();
		boolean res= file.delete();
//		File[] folderList=file.getParentFile().listFiles();
//		System.out.println(res);
//		System.out.println("요기는?");
//		if(res) {
//			System.out.println("야야야");
//			if(!file.getName().equals("webStudy01")) {
//				filist.add(file.getParentFile());
//			}
//			for(File tmp:folderList) {
//				filist.add(tmp);
//				
//			}
//		}
//		
//		return filist;
	}
	public void copyFile(File file) {
		File outFile= new File(file.getAbsolutePath());
		try (
				FileInputStream fis=new FileInputStream(file);
				FileOutputStream out= new FileOutputStream(outFile+".copy");
				){
			byte[] buffer= new byte[1024];
			int pointer = -1;
			while ((pointer = fis.read(buffer)) != -1) {// -1 : EOF문자
				out.write(buffer, 0, pointer);// 1.5kb 일경우 읽어들인 만큼만 복사됨
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
		file.getAbsolutePath();
	}
	public void moveFile(File file,String movePath) {
		File outFile= new File(movePath+"\\"+file.getName());
		file.renameTo(outFile);
	}
}
