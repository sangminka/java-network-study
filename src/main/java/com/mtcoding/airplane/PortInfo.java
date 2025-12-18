package com.mtcoding.airplane;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class PortInfo {
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

                @Getter
                @Setter
                public static class Item{
                    String airportId;
                    String airportNm;
                }

            }
        }
    }
}
