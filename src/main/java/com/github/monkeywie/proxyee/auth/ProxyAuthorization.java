package com.github.monkeywie.proxyee.auth;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.internal.StringUtil;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * The HTTP Proxy-Authorization request header should contains the credentials
 * to authenticate a user agent to a proxy server:
 * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Proxy-Authorization">Proxy-Authorization</a>
 */
public class ProxyAuthorization {

    private String name;
    private String password;

    public ProxyAuthorization(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean isAuthorisedRequest(HttpRequest request) {
        if (StringUtil.isNullOrEmpty(name) || StringUtil.isNullOrEmpty(password)) {
            return true;
        } else if (!request.headers().contains("Proxy-Authorization")) {
            return false;
        } else {
            String base64 = request.headers().get("Proxy-Authorization").replace("Basic ", "");
            String[] credentials = new String(Base64.getDecoder().decode(base64), StandardCharsets.UTF_8)
                    .split(":");
            if (credentials.length < 2) {
                return false;
            } else {
                return name.equals(credentials[0]) && password.equals(credentials[1]);
            }
        }
    }

}
