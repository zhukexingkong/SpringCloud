package com.learn.consumer_service.service;

import com.learn.consumer_service.bean.User;
import com.learn.consumer_service.config.MyConfiguration;
import com.learn.consumer_service.service.impl.ProviderServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Feign会通过动态代理，帮我们生成实现类。
//Feign中已经自动集成Ribbon负载均衡,不需要restTemplate上的@LoadBalanced注解
@FeignClient(value = "provider-service", fallback = ProviderServiceFallBack.class,configuration = MyConfiguration.class)//指定feign调用的服务
@Primary    // 优先注入该bean
public interface ProviderService {
    @RequestMapping("/user/findById")
    User findById(@RequestParam("id") Integer id);
}
