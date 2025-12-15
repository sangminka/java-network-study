package com.mtcoding.ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Buf03 {
    public static void main(String[] args) {
        // 기능 확장 -> 데코레이터(장식) 패턴
        
        //1. 바이트 스티림 연결
        InputStream in = System.in;
        //2. 숫자를 문자로 변환해주는 것을 설정
        InputStreamReader ir = new InputStreamReader(in);
        //3. 직접 배열을 다는게 아니라, 가변 배열을 달아줌
        BufferedReader br = new BufferedReader(ir);

        try {
            String line = br.readLine();// readline = \n 까지 읽음(버퍼에 있는 것을)
            System.out.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
