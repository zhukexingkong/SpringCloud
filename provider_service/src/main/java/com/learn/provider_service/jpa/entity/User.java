package com.learn.provider_service.jpa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user")// 启动项目时，表不存在或字段不存在会自动创建，更新
@Data
public class User {
    @Id
    @GeneratedValue //主键
    private int id;

    @Column(name="username")
    private String userName;

    private String password;

    private String name;

    private String note;//备注
}
