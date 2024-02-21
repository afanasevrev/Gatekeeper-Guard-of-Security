package com.alrosa.staa.gatekeeper_client.cookie;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

public class CookieInterceptor implements ClientHttpRequestInterceptor {

    private String jSessionId;


    public String getJSessionId() {
        return jSessionId;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);
        HttpHeaders headers = response.getHeaders();
        List<String> cookies = headers.get("Set-Cookie");

        if (cookies != null) {
            for (String cookie : cookies) {
                if (cookie.startsWith("JSESSIONID")) {
                    jSessionId = StringUtils.trimAllWhitespace(cookie.split(";")[0]);
                    break;
                }
            }
        }

        return response;
    }

}