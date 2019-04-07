package com.wsw.test;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wsw.util.DOMUtils;

public class Test01 {

	public static void main(String[] args) throws InterruptedException {
        Thread s = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true){
                        System.out.println("等待");
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    System.out.println("结束");

                }
            }
        });
                s.start();
        Thread.sleep(2000);
        s.interrupt();
    }


}
