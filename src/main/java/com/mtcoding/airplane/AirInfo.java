package com.mtcoding.airplane;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
public class AirInfo {
    private Response response;

    @Getter
    @Setter
    public static class Response{
        private Header header;
        private Body body;

        @Getter
        @Setter
        public static class Header{
            private String resultCode;
            private String resultMsg;
        }

        @Getter
        @Setter
        public static class Body{
            private Items items;

            @Getter
            @Setter
            public static class Items{
                private List<Item> item;
                Integer numOfRows;
                Integer pageNo;
                Integer totalCount;

                @Getter
                @Setter
                public static class Item{
                    String airlineNm;
                    String arrAirportNm;
                    Long arrPlandTime;
                    String depAirportNm;
                    Long depPlandTime;
                    String vihicleId;
                    Integer economyCharge;
                    Integer prestigeCharge;
                }

            }
        }
    }
}
