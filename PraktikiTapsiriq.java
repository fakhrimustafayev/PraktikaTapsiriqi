/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.company.praktikitapsiriq;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class PraktikiTapsiriq {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name");
        String name = sc.nextLine();

        System.out.println("Enter surname");
        String surname = sc.nextLine();

        System.out.println("Enter the file which you want to send");
        String filePath = sc.nextLine();

        System.out.println("Enter the ip and port of server");
        String str = sc.nextLine() + ":" + sc.nextInt();
        System.out.println(str);

        String[] arr = str.split(":");
        String ip = arr[0];
        int port = Integer.parseInt(arr[1]);
        
        try{
            Socket socket = new Socket(ip,port);
            
            File file = new File(filePath);
            byte[] fileBytes = new byte[(int) file.length()];
            
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(fileBytes, 0, fileBytes.length);
            
            OutputStream os = socket.getOutputStream();
            os.write(fileBytes, 0, fileBytes.length);
            os.flush();
            
            socket.close();
            System.out.println("File sent successfully.");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
