package com.learn.consumer_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class httpTest {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/{id}")
    public String hello(@PathVariable("id") Integer id){
        String url = "http://localhost:8080/user/findById?id="+id;
        /**
         * 获取用户提供者的服务，进行调用
         */
        //获取服务列表
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
//        ServiceInstance serviceInstance = instances.get(0);//当前用户服务
//        String host = serviceInstance.getHost();//服务的地址
//        int port = serviceInstance.getPort();//服务端口
        //获取新的访问地址，动态
//        url = "http://"+host+":"+port+"/user/findById?id="+id;

        String jsonResult = restTemplate.getForObject(url, String.class);
        return jsonResult;
    }
}
