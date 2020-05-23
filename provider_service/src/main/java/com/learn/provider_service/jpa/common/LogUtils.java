package com.learn.provider_service.jpa.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LogUtils {
    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);
    public static String getRequestLog(HttpServletRequest request) {
        StringBuffer logBuffer = new StringBuffer();
        logBuffer.append("getMethod : " + request.getMethod());
        logBuffer.append("getRequestURI:" + request.getRequestURL());
        logBuffer.append("getQueryString:" + request.getQueryString());
        logBuffer.append("getContextPath:" + request.getContextPath());
        logBuffer.append("getServletPath:" + request.getServletPath());
        logBuffer.append("getRemoteAddr : " + request.getRemoteAddr());
        logBuffer.append("getRemoteHost : " + request.getRemoteHost());
        logBuffer.append("getRemotePort : " + request.getRemotePort());
        logBuffer.append("getLocalAddr : " + request.getLocalAddr());
        logBuffer.append("getLocalName : " + request.getLocalName());
        logBuffer.append("getLocalPort : " + request.getLocalPort());
        logBuffer.append("getServerName : " + request.getServerName());
        logBuffer.append("getServerPort : " + request.getServerPort());
        logBuffer.append("getRequestURL : " + request.getRequestURL());
        return logBuffer.toString();
    }
}
