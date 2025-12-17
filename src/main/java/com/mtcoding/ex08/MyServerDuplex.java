package com.mtcoding.ex08;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServerDuplex {

    public static void main(String[] args) {
        try {
            // 서버 소켓 생성
            ServerSocket ss = new ServerSocket(20000);
            System.out.println("[server] 대기중...");

            // 서버 소켓에 클라이언트 연결되면 연결 소켓 대신 연결
            Socket socket = ss.accept();
            System.out.println("[server] 연결됨: " + socket.getInetAddress());

            // 클라이언트 -> 서버 in
            Scanner socketSc = new Scanner(socket.getInputStream());
            // 서버 -> 클라이언트 out
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            // 키보드 입력 읽기
            Scanner keyboardSc = new Scanner(System.in);

            // 1) 수신 스레드: 클라이언트 -> 서버 in
            Thread receiver = new Thread(() -> {
                try {
                    // 클라이언트 -> 서버 있을때 true
                    while (socketSc.hasNextLine()) {
                        // String line에 받은 data를 string 형태로 저장
                        String line = socketSc.nextLine();
                        // 서버에 받은 데이터 출력
                        System.out.println("[server][recv] " + line);

                        // 자동 응답도 가능
                        // pw.println("ok: " + line);
                        // 클라이언트에서 quit 과 들어오면 실행
                        if ("quit".equalsIgnoreCase(line)) {
                            // 연결된 소켓을 닫음
                            try { socket.close(); } catch (Exception ignored) {}
                            break;
                        }
                    }
                } catch (Exception e) {
                    // 예외가 있을경우 종료
                    System.out.println("[server] receiver 종료: " + e.getMessage());
                }
            });

            // 2) 송신 스레드: 서버 -> 클라이언트 (서버 키보드 입력) out
            Thread sender = new Thread(() -> {
                try {
                    // 항상 실행
                    while (true) {
                        // 키보드 입력을 msg 저장
                        String msg = keyboardSc.nextLine();
                        // 서버에서 입력한 msg 출력 자동 flush
                        pw.println(msg);
                        // 키보드로 quit 입력하면 socket 닫기
                        if ("quit".equalsIgnoreCase(msg)) {
                            try { socket.close(); } catch (Exception ignored) {}
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("[server] sender 종료: " + e.getMessage());
                }
            });
            
            // thread 시작 메서드
            receiver.start();
            sender.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}