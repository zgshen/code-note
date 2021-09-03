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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Task {

    private static String cookie;
    private static String domain = "http://tieba.baidu.com";
    static {
        Properties propertise = new Properties();
        String path = Task.class.getResource("task.properties").getPath();
        try {
            propertise.load(new FileInputStream(new File(path)));
            cookie = propertise.getProperty("tieba.cookie");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        BiConsumer<HttpResponse, Throwable> action = (result, exception) -> {
            Document doc = Jsoup.parse(result.body().toString());
            Elements titles = doc.getElementsByClass("bc");
            String title = titles.get(0).text().split(" ")[0];
            System.out.println(Thread.currentThread().getName().concat(",").concat(title).concat("签到成功"));
        };

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
        links.forEach(item -> {
            HttpRequest req = builder.uri(URI.create(domain.concat(item)))
                    .header("Cookie", cookie)
                    .build();
            HttpResponse<String> resp = null;
            try {
                resp = client.send(req, HttpResponse.BodyHandlers.ofString());
                Document doc = Jsoup.parse(resp.body());
                Elements aLabels = doc.select("a[href]");
                for (Element ele : aLabels) {
                    String ss = "http://tieba.baidu.com" + ele.attr("href");
                    if (ss.contains("sign")) {
                        CompletableFuture<HttpResponse<String>> future =
                                client.sendAsync(HttpRequest.newBuilder().uri(URI.create(ss)).header("Cookie",
                                        Task.cookie).build(), HttpResponse.BodyHandlers.ofString())
                                        .whenComplete(action);
                        completableFutureList.add(future);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture[] completableFutures = completableFutureList.stream().toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures);
        System.out.println("结束签到任务...");
    }

}


