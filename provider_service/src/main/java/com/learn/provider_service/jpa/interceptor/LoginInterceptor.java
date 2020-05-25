package com.learn.provider_service.jpa.interceptor;

import com.learn.provider_service.jpa.common.LogUtils;
import com.learn.provider_service.jpa.common.RedisManager;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
public class LoginInterceptor implements HandlerInterceptor {

    private static final Log logger = LogFactory.getLog(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {

            logger.info(LogUtils.getRequestLog(request));
            //校验用户是否已经登录,如果登录过,将之前用户踢掉,同时更新缓存中用户信息
            Subject subject = SecurityUtils.getSubject();
            Serializable sessionId = subject.getSession().getId();
            RedisManager redisManager = RedisManager.getRedisSingleton();
            //获取用户id
            String userId = redisManager.get("sys:login:user_token_"+sessionId.toString());
            if(StringUtils.isNotBlank(userId)) {
                String sessionIdPre = redisManager.get("sys:user:id_"+userId);
                if(!sessionId.equals(sessionIdPre)) {
                    //重定向到login.html
                    redirect(request, response);
                    return false;
                }else {
                    Long expire = redisManager.ttl("sys:login:user_token_"+sessionId.toString());
                    //过期时间小于1分钟的,更新token
                    if(expire < 1 * 60 * 1000) {
                        redisManager.expire("sys:login:user_token_"+sessionId.toString(), 60*30);
                    }
                }
            }else {
                redirect(request, response);
                return false;
            }
        } catch (Exception e) {
            logger.info("preHandle="+e.getMessage());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
    //对于请求是ajax请求重定向问题的处理方法
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取当前请求的路径
        String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort()+request.getContextPath();
//        response.getOutputStream().write("账号在别处登录。".getBytes("UTF-8"));
        //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理 否则直接重定向就可以了
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向的路径
            response.setHeader("CONTENTPATH", basePath+"/login");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else{
            response.sendRedirect(basePath + "/login");
        }
    }
}
