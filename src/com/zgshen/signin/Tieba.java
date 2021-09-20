package com.zgshen.signin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Tieba {


    private static String cookie;
    private static String domain = "http://tieba.baidu.com";

    static {
        Properties propertise = new Properties();
        String path = "/home/tieba/task.properties";
        try {
            propertise.load(new FileInputStream(new File(path)));
            cookie = propertise.getProperty("tieba.cookie");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        System.out.println();
        System.out.println("===================================");
        System.out.println("日期：" + LocalDateTime.now());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder builder = HttpRequest.newBuilder();

        //http://tieba.baidu.com/mo/m?tn=bdFBW&tab=favorite 用户关注的贴吧链接
        HttpRequest request = builder.uri(URI.create(domain.concat("/mo/m?tn=bdFBW&tab=favorite")))
                .header("Cookie", cookie)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Document document = Jsoup.parse(response.body());
        List<String> links = document.select("a[href]").stream()
                .filter(item -> item.attr("href").contains("kw="))
                .map(item -> item.attr("href"))
                .collect(Collectors.toList());
        System.out.println("获取到关注个数：" + links.size());

        System.out.println("开始签到任务...");
        List<CompletableFuture> completableFutureList = new ArrayList<>();

        BiConsumer<HttpResponse, Throwable> action = (result, exception) -> {
            Document doc = Jsoup.parse(result.body().toString());
            Elements aLabels = doc.select("a[href]");
            for (Element ele : aLabels) {
                String url = domain.concat(ele.attr("href"));
                if (url.contains("sign")) {
                    try {
                        client.sendAsync(HttpRequest.newBuilder().uri(URI.create(url))
                                .header("Cookie", cookie)
                                .build(), HttpResponse.BodyHandlers.ofString())
                                .thenAccept(res -> {
                                    Document d = Jsoup.parse(res.body());
                                    Elements titles = d.getElementsByClass("bc");
                                    String title = titles.get(0).text().split(" ")[0];
                                    System.out.println(
                                            Thread.currentThread().getName().concat(",").concat(title).concat("签到成功"));
                                }).get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        links.forEach(item -> {
            HttpRequest req = builder.uri(URI.create(domain.concat(item)))
                    .header("Cookie", cookie)
                    .build();
            CompletableFuture<HttpResponse<String>> future = client.sendAsync(req,
                    HttpResponse.BodyHandlers.ofString()).whenComplete(action);
            completableFutureList.add(future);
        });

        CompletableFuture[] completableFutures = completableFutureList.stream().toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).get();
        System.out.println("结束签到任务...");
    }
}
