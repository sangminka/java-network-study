package com.mtcoding.ex04;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer2 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(20000);
            Socket socket = ss.accept();

            InputStream in = socket.getInputStream();
            InputStreamReader ir =new InputStreamReader(in);
            BufferedReader br = new BufferedReader(ir);

            while (true){
                String line = br.readLine();
                System.out.println("[Server]" + line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
