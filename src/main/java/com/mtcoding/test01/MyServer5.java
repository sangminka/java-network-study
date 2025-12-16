package com.mtcoding.test01;

import com.google.gson.Gson;
import com.mtcoding.ex06.Person;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer5 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(20000);
            Socket socket = ss.accept();
            // 읽기 버퍼
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 쓰기 버퍼
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);

            while (true){

                String line = br.readLine();
                Gson gson = new Gson();
                Person p = gson.fromJson(line,Person.class);
                
                if (p.getAge() >= 20){
                    pw.println("성인");    
                }else {
                    pw.println("청소년");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
