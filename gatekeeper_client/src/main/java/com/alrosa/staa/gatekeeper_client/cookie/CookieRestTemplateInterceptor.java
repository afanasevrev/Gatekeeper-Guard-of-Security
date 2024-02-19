package com.alrosa.staa.gatekeeper_client.cookie;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.List;

public class CookieRestTemplateInterceptor implements ClientHttpRequestInterceptor {
    private List<String> cookies;

    public CookieRestTemplateInterceptor(List<String> cookies) {
        this.cookies = cookies;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (cookies != null) {
            request.getHeaders().put("Cookie", cookies);
        }
        return execution.execute(request, body);
    }
}
