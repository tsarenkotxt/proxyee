package com.github.monkeywie.proxyee;

import com.github.monkeywie.proxyee.auth.ProxyAuthorization;
import com.github.monkeywie.proxyee.server.HttpProxyServer;

/**
 * @Author LiWei
 * @Description
 * @Date 2019/9/23 17:30
 */
public class HttpProxyServerApp {
    public static void main(String[] args) {
        System.out.println("start proxy server");
        new HttpProxyServer()
                .proxyAuthorization(new ProxyAuthorization(
                        System.getProperty("name", ""),
                        System.getProperty("password", "")))
                .port(System.getProperty("port", "9999"))
                .start();
    }
}
