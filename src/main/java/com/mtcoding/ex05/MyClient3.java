package com.mtcoding.ex05;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyClient3 {
    public static void main(String[] args) {
        try {
            String recv;
            Socket socket = new Socket("127.0.0.1",20000);

            // 키보드 읽기
            Scanner sc = new Scanner(System.in);
//            Scanner br = new Scanner(socket.getInputStream());

            // 쓰기 버퍼
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);

            //읽기 버퍼
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            Scanner br = new Scanner(socket.getInputStream());

            while (true){
                String keyboardData = sc.nextLine();
                pw.println(keyboardData);   // ln이 \n 자동으로 넣어주고, autoflush가 된다.
                recv= br.readLine();
                System.out.println("서버로 부터 받은 메시지: " + recv);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
