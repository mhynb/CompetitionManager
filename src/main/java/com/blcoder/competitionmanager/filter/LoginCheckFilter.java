package com.blcoder.competitionmanager.filter;

import com.blcoder.competitionmanager.common.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    // 路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：" + requestURI);

        // 定义无需登录的路径
        String[] urls = new String[]{
                "/admin/**",
                "/normal/**",
                "/user/**",
                "/front/Page/login.html",
                "/front/Page/register.html",
                "/front/images/**"
        };

        // 判断是否需要放行
        boolean check = check(urls, requestURI);
        if (check) {
            log.info("本次请求 {} 不需要处理", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 检查用户登录状态
        if (request.getSession().getAttribute("user") != null) {
            log.info("用户已登录，用户ID为：{}", request.getSession().getAttribute("user"));
            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request, response);
            return;
        }

        // 用户未登录，重定向到登录页面
        log.info("用户未登录，跳转到登录页面");
        response.sendRedirect(request.getContextPath() + "/front/Page/login.html");
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
