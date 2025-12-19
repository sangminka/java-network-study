package com.mtcoding.ex11;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class PostApp {

    static Map<String, String> busNum = new HashMap<>();

    public static String getData(String inputUrl) throws Exception {

        URL url = new URL(inputUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        // 2. PortInfo로 오브젝트화 시켜 !!
        Scanner sc = new Scanner(conn.getInputStream());
        String portJson = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            portJson = portJson + line;
        }

        return portJson;
    }

    /**
     * 메인
     * @param args
     */

    public static void main(String[] args) {
        // 찾은 버스 노선 리스트
        List<BusRoute> list = new ArrayList<>();
        //  버스 정류소 ID
        String nodeId = "";
        // 버스ID
        String routeId="";

        Gson gson = new Gson();
        Scanner keyBoard = new Scanner(System.in);
        try {
            System.out.println("버스정류소를 입력해주세요: ");
            String nodeNm = keyBoard.nextLine();
            String encoded = URLEncoder.encode(nodeNm, StandardCharsets.UTF_8);


            // 1. NodeID 찾기 입력: 버스정류소 받아서
            String url1 = """
                    https://apis.data.go.kr/1613000/BusSttnInfoInqireService/getSttnNoList?serviceKey=Kn6wh2%2F6NRM2R0j%2FdC%2Bt882mefUdk6X5srF4hXd%2B5GOLavX1DD7xOjxd5MCDyWagmF7IAjj4SEjSOYO9DPUZtg%3D%3D&pageNo=1&numOfRows=10&_type=json&cityCode=21&nodeNm=${nodeNm}
                    """.replace("${nodeNm}",encoded);
            String data1 = getData(url1);
            System.out.println(data1);

            BusStop busStop = gson.fromJson(data1, BusStop.class);

            List<BusStop.Response.Body.Items.Item> nodeIdList = busStop.getResponse().getBody().getItems().getItem();

            nodeId = nodeIdList.get(0).getNodeid();
        } catch (Exception e) {
            System.out.println("111111111111111111111111111111111111");
        }

        // 2. nodeID를 이용해서 도착 버스 확인

        try {
            String url2 = """
                    https://apis.data.go.kr/1613000/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList?serviceKey=Kn6wh2%2F6NRM2R0j%2FdC%2Bt882mefUdk6X5srF4hXd%2B5GOLavX1DD7xOjxd5MCDyWagmF7IAjj4SEjSOYO9DPUZtg%3D%3D&pageNo=1&numOfRows=10&_type=json&cityCode=21&nodeId=${nodeId}
                    """.replace("${nodeId}",nodeId);
            String data2 = getData(url2);
            System.out.println(data2);

            BusArriveInfo busArriveInfo = gson.fromJson(data2, BusArriveInfo.class);
            List<BusArriveInfo.Response.Body.Items.Item> arriveInfo = busArriveInfo.getResponse().getBody().getItems().getItem();

            
            //버스 번호 받기
            for (BusArriveInfo.Response.Body.Items.Item item : arriveInfo) {
                busNum.put(item.getRouteno(), item.getRouteid());
            }
            System.out.println("버스 번호를 선택해주세요");
            System.out.println(busNum.keySet());
            System.out.print("번호: ");
            String num = keyBoard.nextLine();
            routeId = busNum.get(num);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //3 버스 경로 체크

        try {

            int count = 1;
            while (true) {
                String url3 = """
                        https://apis.data.go.kr/1613000/BusRouteInfoInqireService/getRouteAcctoThrghSttnList?serviceKey=Kn6wh2%2F6NRM2R0j%2FdC%2Bt882mefUdk6X5srF4hXd%2B5GOLavX1DD7xOjxd5MCDyWagmF7IAjj4SEjSOYO9DPUZtg%3D%3D&pageNo=${count}&numOfRows=10&_type=json&cityCode=21&routeId=${routeId}
                        """.replace("${count}", count + "").replace("${routeId}",routeId);
                String data3 = getData(url3);

                try {
                    BusRoute busRoute = gson.fromJson(data3, BusRoute.class);
                    list.add(busRoute);
                    count++;
                } catch (Exception e) {
//                    System.out.println(e.getMessage());
                    break;
                }
            } // end of while

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).getResponse().getBody().getItems().getItem().size(); j++) {
                    System.out.print(list.get(i).getResponse().getBody().getItems().getItem().get(j).getNodenm() + " ");
                }
                System.out.println();
            }
//            System.out.println(list.get(i));

        } catch (Exception e) {

        }
    }

}
