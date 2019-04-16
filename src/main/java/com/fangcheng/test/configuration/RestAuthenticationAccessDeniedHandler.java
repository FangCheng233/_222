package com.fangcheng.test.configuration;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.configuration
 * @ClassName: RestAuthenticationAccessDeniedHandler
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/15 20:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/15 20:27
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);/*400错误*/
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);/*403错误*/
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);/*404错误*/
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);/*405错误*/
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);/*500错误*/
        System.out.println(HttpServletResponse.SC_NOT_FOUND);
        PrintWriter writer = response.getWriter();
        writer.println(accessDeniedException.getMessage());
    }
}

