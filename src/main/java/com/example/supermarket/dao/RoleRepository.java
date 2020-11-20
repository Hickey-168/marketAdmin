package com.example.supermarket.dao;

import com.example.supermarket.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public List<Role> findAllByRoleName(String roleName);
    public void deleteByRoleName(String roleName);
    public Role getRoleByRoleId(Integer roleId);
    public Role findRoleByRoleId(Integer roleId);
}
