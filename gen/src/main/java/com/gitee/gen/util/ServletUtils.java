package com.gitee.gen.util;

import com.gitee.gen.constant.ContentTypeConstant;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * servlet工具类
 * @author mtian
 */
public class ServletUtils {


    /**
     * 将字符串渲染到客户端
     *
     * @param response response
     * @param repoStr  渲染的字符串
     * @return null
     */
    public static String responseString(HttpServletResponse response, String repoStr) {
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(ContentTypeConstant.application_json);
            response.setCharacterEncoding(Charset.defaultCharset().name());
            response.getWriter().print(repoStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
