package com.mtcoding.ex06;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer4 {
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

                System.out.println(p.getNo());
                System.out.println(p.getName());
                System.out.println(p.getAge());
                System.out.println(p.getHobby().get(0));
                System.out.println(p.getHobby().get(1));


                pw.println("ok");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
