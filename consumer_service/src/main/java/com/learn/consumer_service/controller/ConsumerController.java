package com.learn.consumer_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * discoveryClient 服务注册中心客户端对象，存储了服务的访问地址
     */
    @Autowired
    DiscoveryClient discoveryClient;

//    @RequestMapping("/consumer/{id}")
//    public String hello(@PathVariable("id") Integer id){
//        //String url = "http://user-service/user/findById?id="+id;
//        /**
//         * 获取用户提供者的服务，进行调用
//         */
//        //获取服务列表
//        List<ServiceInstance> instances = discoveryClient.getInstances("provider-service");
//        ServiceInstance serviceInstance = instances.get(0);//当前用户服务
//        String host = serviceInstance.getHost();//服务的地址
//        int port = serviceInstance.getPort();//服务端口
//        //获取新的访问地址，动态
//        String url = "http://"+host+":"+port+"/user/findById?id="+id;
//
//        String jsonResult = restTemplate.getForObject(url, String.class);
//        return jsonResult;
//    }

    @RequestMapping("/consumer/{id}")
    public String findById(@PathVariable("id") Integer id){
        String url = String.format("http://provider-service/user/findById?id=%d", id);
        String jsonResult = restTemplate.getForObject(url, String.class);
        return jsonResult;
    }

}
