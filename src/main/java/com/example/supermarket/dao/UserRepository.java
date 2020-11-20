package com.example.supermarket.dao;

import com.example.supermarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAll();
    void deleteByUserName(String userName);
    User findByUserName(String userName);
    User findByUserId(Integer userId);
}
