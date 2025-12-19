package com.mtcoding.ex11;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class BusStop {
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
                    private Double gpslati;
                    private Double gpslong;
                    private String nodeid;
                    private String nodenm;
                    private String nodeno;

                }


            }
        }


    }
}
