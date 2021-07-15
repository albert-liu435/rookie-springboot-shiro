package com.rookie.bigdata.shiro.filter;

import com.rookie.bigdata.shiro.OAuth2Token;
import com.rookie.bigdata.util.JWTUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname OAuth2Filter
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/13 11:50
 * @Version 1.0
 */
public class OAuth2Filter extends AuthenticatingFilter {


    /**
     * 从请求中获取token并封装成OAuth2Token
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //从请求中获取携带的token,并封装成Auth2Token对象返回
        String token = getRequestToken((HttpServletRequest) request);

        if (StringUtils.isEmpty(token)) {
            return null;
        }

        return new OAuth2Token(token);
    }

    /**
     * 获取请求header或请求体中的token信息
     *
     * @param request
     * @return
     */
    private String getRequestToken(HttpServletRequest request) {
        //从header中获取token
        String token = request.getHeader(JWTUtils.TOKEN_HEADER);

        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(JWTUtils.TOKEN_HEADER);
        }

        return token;
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //如果为OPTIONS方法直接放行通过
        return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        if (StringUtils.isEmpty(token)) {
            //表示没有携带token,直接拒绝
            return false;
        }

        //进行登录操作
        return executeLogin(request, response);
    }
}
