package com.mtcoding.ex05;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer3 {
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
                System.out.println("[Server]" + line);
                pw.println("ok");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
