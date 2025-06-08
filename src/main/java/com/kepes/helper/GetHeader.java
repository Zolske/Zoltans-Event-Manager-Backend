package com.kepes.helper;

import org.springframework.http.HttpHeaders;

public class GetHeader {

    public static HttpHeaders success(String message){
        HttpHeaders headers = new HttpHeaders();
        headers.set("msg", message);
        headers.set("isSuccess", String.valueOf(true));
        return headers;
    }

    public static HttpHeaders unSuccess(String message){
        HttpHeaders headers = new HttpHeaders();
        headers.set("msg", message);
        headers.set("isSuccess", String.valueOf(false));
        return headers;
    }
}
