package com.example.supermarket.service;

import com.example.supermarket.dao.RoleRepository;
import com.example.supermarket.dao.UserRepository;
import com.example.supermarket.entity.Role;
import com.example.supermarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Role> listByUserId(Integer userId){
        List<Role> roles = new ArrayList<>();
        User user = userRepository.findByUserId(userId);
        System.out.println("listByUserId方法里的userId为: " + userId);
        System.out.println("user.getRoles 为: "+ user.getRoles());
        for (Role role : user.getRoles() ){
            role.toString();
//            Integer roleId = role.getRoleId();
//            role = roleRepository.getRoleByRoleId(roleId);
//            roles.add(role);
        }
        return  roles;
    }

    public Role findRoleById(Integer id){
        return roleRepository.findRoleByRoleId(id);
    }
}
