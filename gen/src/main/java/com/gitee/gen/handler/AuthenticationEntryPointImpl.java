package com.gitee.gen.handler;

import com.alibaba.fastjson.JSON;
import com.gitee.gen.common.Action;
import com.gitee.gen.util.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理类
 * @author mtian
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {
        String msg = String.format("请求访问：{},认证失败",httpServletRequest.getRequestURI());
        ServletUtils.responseString(httpServletResponse, JSON.toJSONString(Action.err(msg)));
    }
}
