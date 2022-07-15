package com.swordoffer;

import java.io.File;

/**
 * 目录创建
 */
public class SwordOffer {

    public static void main(String[] args) {
        for(int i=15; i<70; i++) {
            String d = "D:\\gitFile\\code-note\\src\\com\\swordoffer\\offer"+i;
            File file = new File(d);
            if (!file.exists())
                file.mkdir();
        }
    }

}
