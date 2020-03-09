package com.learn.provider_service.mappers;

import com.learn.provider_service.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    void save(User user);
    void delete(Integer id);
    void update(User user);
    List<User> findAll();
    User findById(Integer id);
}
