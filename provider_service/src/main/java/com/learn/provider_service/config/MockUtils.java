package com.learn.provider_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author HeBiao
 * @Date 2021/4/30 16:33
 * @Description 模拟数据控制器
 */
@Configuration
@ConfigurationProperties(prefix = "mock")
public class MockUtils {
    @Value("${mock.mockFlag:false}")
    private static String mockFlag;

    public void setMockFlag(String mockFlag) {
        MockUtils.mockFlag = mockFlag;
    }

    public static String getMockFlag(){
        return mockFlag;
    }
}
