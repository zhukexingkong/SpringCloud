package com.learn.provider_service.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private String name;
}
