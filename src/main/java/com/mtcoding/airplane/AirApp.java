package com.mtcoding.airplane;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AirApp {
    // key = 무안 , value = NAARKJB
    static Map<String,String> ports= new HashMap<>();

    public static void main(String[] args) {

        try {
            // 1. 공항정보를 다운 - HttpUrlConnection으로
            URL url = new URL("https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getArprtList?serviceKey=Kn6wh2%2F6NRM2R0j%2FdC%2Bt882mefUdk6X5srF4hXd%2B5GOLavX1DD7xOjxd5MCDyWagmF7IAjj4SEjSOYO9DPUZtg%3D%3D&_type=json");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            // 2. PortInfo로 오브젝트화 시켜 !!
            Scanner sc= new Scanner(conn.getInputStream());
            String portJson = "";
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                portJson = portJson + line;
            }

            Gson gson = new Gson();
            PortInfo portInfo = gson.fromJson(portJson, PortInfo.class);

            // 3. ports에 airportId에 있는 값, airportNm에 있는 값
            List<PortInfo.Response.Body.Items.Item>portItem =portInfo.getResponse().getBody().getItems().getItem();

            for (int i = 0; i < portItem.size() ; i++) {
                String temp = portItem.get(i).airportId;
                String temp2 = portItem.get(i).airportNm;
                AirApp.ports.put(temp2,temp);
            }

            System.out.println(AirApp.ports);




            String dep = ""; // 무안
            String depkey = ""; // 무안
            String arr = ""; // 부산
            String arrkey = ""; // 부산
            String time = ""; //20251219
            // 4. 스캐너로 출발지를 받기
            System.out.println("출발지를 입력해주세요");
            System.out.println("사천,군산,청주,울산,김해,대구,광주,김포,포항,제주,무안,인천,양양,원주,여수");
            Scanner keyBoard = new Scanner(System.in);
            dep = keyBoard.nextLine();
            depkey =AirApp.ports.get(dep);
            System.out.println("출발지: "+dep + " 코드: " + depkey);
            // 5. 스캐너로 목적지 받기
            System.out.println("목적지를 입력해주세요");
            System.out.println("사천,군산,청주,울산,김해,대구,광주,김포,포항,제주,무안,인천,양양,원주,여수");
            
            arr = keyBoard.nextLine();
            
            arrkey =AirApp.ports.get(arr);
            System.out.println("도착지: "+arr + " 코드: " + arrkey);
            // 6. 날짜받기
            System.out.println("날짜를 입력해주세요(ex:20251218) 형태를 지켜주세요");
            time = keyBoard.nextLine();
            System.out.println("입력한 날짜는: " + time);

            // 7. 동적인 URL 만들기
            String inputUrl = """
                https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=Kn6wh2%2F6NRM2R0j%2FdC%2Bt882mefUdk6X5srF4hXd%2B5GOLavX1DD7xOjxd5MCDyWagmF7IAjj4SEjSOYO9DPUZtg%3D%3D&pageNo=1&numOfRows=10&_type=json&depAirportId=${depkey}&arrAirportId=${arrkey}&depPlandTime=${time}
                """.replace("${depkey}",depkey).replace("${arrkey}",arrkey).replace("${time}",time);

            // 8. 항공스케줄 다운 - HttpUrlConnection
            URL url2 = new URL(inputUrl);
            HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();
            conn2.setRequestMethod("GET");
            // 9. AirInfo로 파싱
            Scanner sc2 =new Scanner(conn2.getInputStream());
            String airInfoJson = "";

            while (sc2.hasNextLine()){
                String line = sc2.nextLine();
                airInfoJson = airInfoJson + line;
            }
            System.out.println(airInfoJson);
            AirInfo airInfo = gson.fromJson(airInfoJson, AirInfo.class);
            // 10. 항공스케줄 이쁘게 전체 출력
            List<AirInfo.Response.Body.Items.Item> airInfoList =airInfo.getResponse().getBody().getItems().getItem();

            for (int i = 0; i < airInfoList.size(); i++) {
                System.out.println(airInfoList.get(i).getAirlineNm());
                System.out.println(airInfoList.get(i).getArrAirportNm());
                System.out.println(airInfoList.get(i).getArrPlandTime());
                System.out.println(airInfoList.get(i).getDepAirportNm());
                System.out.println(airInfoList.get(i).getDepPlandTime());
                System.out.println(airInfoList.get(i).getVihicleId());
                System.out.println(airInfoList.get(i).getEconomyCharge());
                System.out.println(airInfoList.get(i).getPrestigeCharge());
//
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }



    }
}
