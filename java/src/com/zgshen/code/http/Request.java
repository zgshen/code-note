package com.zgshen.code.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nathan
 * @date 2020/8/8 12:04
 * @desc Request
 */
public class Request {

    public String method; // 请求方法
    public String path; // 请求路径
    public String portal; // 协议名称
    public Map<String, String> map; // 请求头中的附加参数
    public String payload; // 请求体

    private int BUFFER_LEN = 10240;

    public Request(InputStream in) throws IOException {
        byte[] buf = new byte[BUFFER_LEN];
        int len = in.read(buf);
        String reqString = new String(buf, 0, len);
        String raw = reqString;
        int i = 0, j = 0;

        // 获取请求方式
        for (; i < raw.length(); i++)
            if (raw.charAt(i) == ' ') {
                this.method = raw.substring(j, i);
                break;
            }
        j = ++i;
        // 获取请求路径
        for (; i < raw.length(); i++)
            if (raw.charAt(i) == ' ') {
                this.path = raw.substring(j, i);
                break;
            }
        j = ++i;
        // 获取协议版本
        for (; i < raw.length(); i++)
            if (raw.charAt(i) == '\r') {
                this.portal = raw.substring(j, i);
                break;
            }
        j = (i += 2);

        this.map = new HashMap<String, String>();
        String key = "", value = "";
        while (i < raw.length()) {
            if (raw.charAt(i) != ':') {
                i++;
            } else {
                key = raw.substring(j, i);
                j = (i += 2);
                while (i != raw.length() && raw.charAt(i) != '\r')
                    i++;
                value = raw.substring(j, i);
                map.put(key, value);
                if (i == raw.length()) {
                    break;
                } else {
                    j = (i += 2);
                    if (raw.charAt(i) == '\r') {
                        payload = raw.substring(i + 2);
                    }
                }
            }

        }

    }

    @Override
    public String toString() {
        return "Request [method=" + method + ", path=" + path + ", portal=" + portal + ", map=" + map + ", payload="
                + payload + "]";
    }

}
