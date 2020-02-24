//package com.itheima.controller;
//
//import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/consumer")
//@DefaultProperties(defaultFallback = "defaultFallback")
//public class HelloController {
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Autowired
//    DiscoveryClient discoveryClient;
//
//    @RequestMapping("{id}")
////    @HystrixCommand(fallbackMethod = "findByIdFallback")
//    @HystrixCommand
//    public String findById(@PathVariable("id") Integer id){
//        String url = "http://user-service/user/findById?id="+ id;
////        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
////        ServiceInstance serviceInstance = instances.get(0);
////        String host = serviceInstance.getHost();
////        int port = serviceInstance.getPort();
////
////        url = "http://"+host+":"+port+"/user/findById?id="+ id;
//        String result = restTemplate.getForObject(url, String.class);
//        return result;
//    }
//
//    public String findByIdFallback(Integer id){
//        return "您好，服务正忙，请您稍后尝试！！！！！";
//    }
//    public String defaultFallback(){
//        return "【全局默认的服务降级方法】您好，服务目前正在排队访问，请您稍后尝试！！！！！";
//    }
//}
