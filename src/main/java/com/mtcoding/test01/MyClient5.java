package com.mtcoding.test01;

import com.google.gson.Gson;
import com.mtcoding.ex06.Person;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class MyClient5 {
    public static void main(String[] args) {
        try {
            String recv;
            Socket socket = new Socket("127.0.0.1", 20000);


            // 쓰기 버퍼
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            //읽기 버퍼
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            Scanner br = new Scanner(socket.getInputStream());

            Person person = new Person(1, "홍길동", 20, Arrays.asList("축구", "농구"));

            Gson gson = new Gson();
            String json = gson.toJson(person);

            pw.println(json);   // ln이 \n 자동으로 넣어주고, autoflush가 된다.
            recv = br.readLine();
            System.out.println("서버로 부터 받은 메시지: " + recv);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
