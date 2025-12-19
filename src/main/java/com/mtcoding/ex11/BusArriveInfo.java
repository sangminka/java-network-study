package com.mtcoding.ex11;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class BusArriveInfo {
    private Response response;

    @Getter
    @Setter
    public static class Response {
        private Header header;
        private Body body;

        @Getter
        @Setter
        public static class Header {
            String resultCode;
            String resultMsg;
        }

        @Getter
        @Setter
        public static class Body {
            private Items items;
            private Integer numOfRows;
            private Integer pageNo;
            private Integer totalCount;

            @Getter
            @Setter
            public static class Items {
                private List<Item> item;

                @Getter
                @Setter
                public static class Item {
                    private Integer arrprevstationcnt;
                    private Integer arrtime;

                    private String nodeid;
                    private String nodenm;
                    private String routeid;
                    private String routeno;
                    private String routetp;
                    private String vehicletp;

                }


            }
        }


    }
}
