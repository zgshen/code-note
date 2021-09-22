package com.jdk.java9to11;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientExample {

    HttpClient client;

    @Before
    public void init() {
        client = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(20000L)).build();
    }

    @Test
    public void reqTest() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder(URI.create("https://zguishen.com/")).build();
        /**
         * {@link jdk.internal.net.http.HttpRequestImpl#HttpRequestImpl(java.net.http.HttpRequest, java.net.ProxySelector)} 109
         * 没有指定协议默认是 GET
         */
        String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        System.out.println(body);
    }

    @Test
    public void getTest() {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/zgshen"))
                .header("Accept", "application/vnd.github.v3+json")
                //.header("Cookie", cookie)
                .timeout(Duration.ofSeconds(10000L))
                .GET()
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .whenCompleteAsync((res, exp) -> {
                    System.out.println(res.body());
                }).join();
    }

    @Test
    public void postTest() {
        var requestBody = "{'key':'val'}";
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://example.com/json"))
                .header("Contend-Type","application/json")
                .timeout(Duration.ofSeconds(10000L))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .whenCompleteAsync((res, exp) -> {
                    System.out.println(res.body());
                }).join();
    }

    @Test
    public void Http2Test() throws URISyntaxException {
        HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NEVER)
                .version(HttpClient.Version.HTTP_2)
                .build()
                .sendAsync(HttpRequest.newBuilder()
                                .uri(new URI("https://zguishen.com/"))
                                .GET()
                                .build(),
                        HttpResponse.BodyHandlers.ofString())
                .whenComplete((resp, t) -> {
                    if (t != null) {
                        t.printStackTrace();
                    } else {
                        System.out.println(resp.version());
                        System.out.println(resp.statusCode());
                    }
                }).join();
    }

}
