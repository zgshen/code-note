package com.zgshen.code.spi;

import java.io.IOException;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Service Provider Interface
 * 在 META-INF/services/ 目录中创建以接口全限定名命名的文件，该文件内容为API具体实现类的全限定名
 * 使用 ServiceLoader 类动态加载 META-INF 中的实现类
 * 如 SPI 的实现类为 Jar 则需要放在主程序 ClassPath 中
 * API 具体实现类必须有一个不带参数的构造方法
 */
public class SPIExp {

    public static void main(String[] args) throws IOException {
        ServiceLoader<Log> loader = ServiceLoader.load(Log.class);
        Iterator<Log> iteratorSpi = loader.iterator();
        while (iteratorSpi.hasNext()) {
            Log log = iteratorSpi.next();
            log.log("output");
        }
    }

}

