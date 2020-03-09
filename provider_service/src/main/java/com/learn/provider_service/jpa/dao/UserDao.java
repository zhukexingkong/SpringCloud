package com.learn.provider_service.jpa.dao;

import com.learn.provider_service.jpa.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
@Table(name="user")
@Qualifier("userDao")
public interface UserDao extends JpaRepository<User, Integer> {

    public User save(User u);

    @Query("select t from User t where t.userName=:name")
    public User findUserByName(@Param("name") String name);
}
