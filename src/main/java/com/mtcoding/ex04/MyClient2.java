package com.mtcoding.ex04;

import java.io.*;
import java.net.Socket;

public class MyClient2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",20000);

            InputStream keyStream = System.in;
            InputStreamReader keyReader = new InputStreamReader(keyStream);
            BufferedReader keyBuf = new BufferedReader(keyReader);

            OutputStream out = socket.getOutputStream();
            OutputStreamWriter ow = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(ow);

            while (true){
                String keyboardData = keyBuf.readLine();
                bw.write(keyboardData);
                bw.write("\n");
                bw.flush();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
