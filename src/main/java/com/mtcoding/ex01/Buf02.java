package com.mtcoding.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Buf02 {
    public static void main(String[] args) {
        // 1. 바이트 스트림 연결
        InputStream in = System.in;

        // 불편한점 : 글자 하나씩(버퍼),숫자를 문자로 캐스팅(char)을 안해준다.
        // 2. 배열을 가질 수 있고, 문자로 캐스팅 해줌;
        InputStreamReader ir = new InputStreamReader(in);
        char[] buf = new char[3];


        try {
            ir.read(buf);   // 키보드 입력 대기 (\n)

            for (char c : buf){
                System.out.print(c+ ",");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
