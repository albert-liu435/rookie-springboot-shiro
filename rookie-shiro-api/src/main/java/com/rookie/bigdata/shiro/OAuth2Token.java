package com.rookie.bigdata.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Classname OAuth2Token
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/13 11:50
 * @Version 1.0
 */
public class OAuth2Token implements AuthenticationToken {
    private static final long serialVersionUID = 1L;
    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
