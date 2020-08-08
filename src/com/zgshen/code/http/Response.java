package com.zgshen.code.http;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * @author nathan
 * @date 2020/8/8 12:05
 * @desc Response
 */
public class Response {

    private OutputStream out;

    public String portal;
    public String status;
    public String statusDescribe;
    public Map<String, String> map;

    public Response(OutputStream out, String portal, String status, String statusDescribe) {
        this.out = out;
        this.portal = portal;
        this.status = status;
        this.statusDescribe = statusDescribe;
    }

    public void writeHeaderAndBody(byte[] b, int off, int len) {
        this.writeHeader();
        this.writeBytes(b, off, len);
    }

    public void addHeader(String key, String value) {
        this.map.put(key, value);
    }

    public void delHeader(String key) {
        if (this.map.containsKey(key))
            this.map.remove(key);
    }

    public void editHeader(String key, String value) {
        if (this.map.containsKey(key))
            this.map.replace(key, value);
    }

    public void writeHeader() {
        try {
            String tmp = portal + " " + status + " " + statusDescribe + "\n";
            System.out.println(tmp);
            this.writeBytes(tmp.getBytes(), 0, tmp.length());
            if (map != null) {
                Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, String> entry = entries.next();
                    String s = entry.getKey() + " " + entry.getValue() + "\n";
                    System.out.println(s);
                    this.writeBytes(s.getBytes(), 0, s.length());
                }
            }
            this.out.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBytes(byte[] b, int off, int len) {
        try {
            this.out.write(b, off, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
