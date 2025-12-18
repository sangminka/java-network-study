package com.mtcoding.ex10;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class UrlConnectionReader {
    public static void main(String[] args) {
        try {
            // 1. ip와 port를 통해 소켓을 만들고 스트림을 연결한다
            URL url = new URL("https://search.naver.com/search.naver?where=nv&sm=top_sug.pre&fbm=0&acr=1&acq=%EB%82%A0&qdt=0&ie=utf8&query=%EB%82%A0%EC%94%A8&ackey=2shmbgqy");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            // 2. 버퍼를 달아야 한다.
            Scanner sc = new Scanner(conn.getInputStream());
            String html = "";
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                html = html + line;


            }
            System.out.println(html);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
