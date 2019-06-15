package com.wsw.test;


import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.wsw.annotation.RPCClass;
import com.wsw.annotation.RPCMethod;

@RPCClass
public class TestAnnotation {

	static List<File> list = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		String path = TestAnnotation.class.getResource("/").getPath();
		System.out.println(path);
		File baseFile = new File(path);
		get(baseFile);
		
		try {
			for (File file : list) {
				String fullPath = file.getPath().replaceAll(".class", "");
				System.out.println(fullPath);
				//F:\D_dev\eclipse-workspace\xwell-rpc\target\testes\com\wsw\consumer\C
				fullPath = fullPath.replace("\\", ".");
				System.out.println(fullPath);
				Class<?> clazz = Class.forName(fullPath);
				RPCClass annotation = clazz.getAnnotation(RPCClass.class);
				if(null == annotation) {
					continue;
				}
				System.out.println("====================="+fullPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static void get(File file) {
		if(file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File f : listFiles) {
				get(f);
			}
		}else {
			System.out.println(file.getName());
			list.add(file);
		}
	}
	
	
	@RPCMethod
	void annoMethod() {
		System.out.println("annoMethod");
	}
	
	void defMethod() {
		System.out.println("defMethod");
	}
}
