package com.example.supermarket.dao;

import com.example.supermarket.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    public List<Authority> findAllByAuthorityName(String authorityName);
    public void deleteByAuthorityName(String authorityName);
}
