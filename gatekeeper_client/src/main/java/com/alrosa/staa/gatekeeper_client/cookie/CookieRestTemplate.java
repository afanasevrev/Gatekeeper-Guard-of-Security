package com.alrosa.staa.gatekeeper_client.cookie;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

public class CookieRestTemplate extends RestTemplate {

    @Override
    public ClientHttpRequest createRequest(URI url, HttpMethod method) throws IOException {
        ClientHttpRequest request = super.createRequest(url, method);

        request.getHeaders().add("Cookie", "SessionID=c5278111878");
        return request;
    }
}
