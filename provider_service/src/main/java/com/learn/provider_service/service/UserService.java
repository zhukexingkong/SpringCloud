package com.learn.provider_service.service;

import com.learn.provider_service.entity.User;
import com.learn.provider_service.mappers.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    private static Logger logger = LogManager.getLogger(UserService.class);

    public void save(User user){
        userMapper.save(user);
    }

    public void delete(Integer id){
        userMapper.delete(id);
    }

    public void update(User user){
        userMapper.update(user);
    }

    @Value("${server.port}")
    private String port;

    public List<User> findAll(){
        logger.info("findAll");
        logger.error("findAll");
        logger.debug("findAll");
        return userMapper.findAll();
    }

    public User findById(Integer id){
//        try {
//            Thread.sleep(3000);// 3 > consumer中的hystrix的timeoutInMilliseconds配置时间，就会调用降级方法
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }
        String info = "端口: " + port + " 被调用";
        logger.info(info);
        User user = userMapper.findById(id);
        user.setNote(info);
        return user;
    }
}
