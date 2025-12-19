package com.mtcoding.ex11;

import com.google.gson.Gson;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.mtcoding.ex11.PostApp.busNum;
import static com.mtcoding.ex11.PostApp.getData;

public class GoodTest {
    public static void main(String[] args) {
        String routeId="";
        Scanner keyBoard = new Scanner(System.in);
        Gson gson = new Gson();

        try {
            String url2 = """
                    https://apis.data.go.kr/1613000/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList?serviceKey=Kn6wh2%2F6NRM2R0j%2FdC%2Bt882mefUdk6X5srF4hXd%2B5GOLavX1DD7xOjxd5MCDyWagmF7IAjj4SEjSOYO9DPUZtg%3D%3D&pageNo=1&numOfRows=10&_type=json&cityCode=21&nodeId=BSB505850000
                    """;
            String data2 = getData(url2);
            System.out.println(data2);

            BusArriveInfo busArriveInfo = gson.fromJson(data2, BusArriveInfo.class);
            List<BusArriveInfo.Response.Body.Items.Item> arriveInfo = busArriveInfo.getResponse().getBody().getItems().getItem();

            for (int i = 0; i < arriveInfo.size(); i++) {
                busNum.put(arriveInfo.get(i).getRouteno(), arriveInfo.get(i).getRouteid());
            }
            System.out.println("버스 번호를 선택해주세요");
            System.out.println(busNum.keySet());
            System.out.print("번호: ");
            String num = keyBoard.nextLine();
            routeId = busNum.get(num);
            System.out.println(routeId);
//
//            System.out.println(routeId);
        } catch (Exception e) {
            System.out.println("sssss");
        }
    }
}
