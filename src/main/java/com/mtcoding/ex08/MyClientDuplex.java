package com.mtcoding.ex08;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClientDuplex {

    public static void main(String[] args) {
        try {
            // 서버 소켓과 client 소켓 바이트 스트림 연결
            Socket socket = new Socket("127.0.0.1", 20000);

            // 키보드 입력 읽기
            Scanner keyboardSc = new Scanner(System.in);
            // 서버 -> 클라이언트 읽기
            Scanner socketSc = new Scanner(socket.getInputStream());
            // 클라이언트 -> 서버
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            // 1) 수신 스레드: 서버 -> 클라이언트
            Thread receiver = new Thread(() -> {
                try {
                    // 서버에서 클라이언트로 들어오면
                    while (socketSc.hasNextLine()) {
                        // recv에 서버에서 들어온 String data 저장
                        String recv = socketSc.nextLine();
                        // 출력
                        System.out.println("[client][recv] " + recv);
                    }
                } catch (Exception e) {
                    System.out.println("[client] receiver 종료: " + e.getMessage());
                }
            });

            // 2) 송신 스레드: 클라이언트 -> 서버 (키보드)
            Thread sender = new Thread(() -> {
                try {
                    // 항상 도는 while 
                    while (true) {
                        // 키보드로 입력한 글을 읽어서 msg에 저장
                        String msg = keyboardSc.nextLine(); // 블로킹
                        // 서버에 보내기
                        pw.println(msg); // autoFlush=true

                        // 종료 명령 예시
                        if ("quit".equalsIgnoreCase(msg)) {
                            try { socket.close(); } catch (Exception ignored) {}
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("[client] sender 종료: " + e.getMessage());
                }
            });

            receiver.start();
            sender.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}