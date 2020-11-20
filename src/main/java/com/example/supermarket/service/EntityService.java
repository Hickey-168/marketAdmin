package com.example.supermarket.service;

import com.example.supermarket.dao.AuthorityRepository;
import com.example.supermarket.dao.RoleRepository;
import com.example.supermarket.dao.UserRepository;
import com.example.supermarket.entity.Authority;
import com.example.supermarket.entity.Role;
import com.example.supermarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EntityService {

    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private RoleRepository roleRepository;
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    private AuthorityRepository authorityRepository;
    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository){
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    public void deleteUser(String userName) {
        List<User> users = userRepository.findAll();
        //如果删除维护端数据，只是把维护端的List清空
        for(User user : users) {
            user.getRoles().clear();
            userRepository.save(user); //执行save()之后才会保存到数据库中
        }
        userRepository.deleteByUserName(userName);
    }

    @Transactional
    public void deleteRole(String roleName) {
        List<Role> roles = roleRepository.findAllByRoleName(roleName);
        List<User> users = userRepository.findAll();
        for (User user : users) {
            List<Role> userRole = user.getRoles();
            for (Role role : roles) {
                if (userRole.contains(role)) {
                    userRole.remove(role);
                }
                role.getAuthorities().clear();
                roleRepository.save(role);
            }
            userRepository.save(user);
        }
        roleRepository.deleteByRoleName(roleName);
    }

    @Transactional
    public void deleteAuthority(String authName) {
        List<Authority> authorities = authorityRepository.findAllByAuthorityName(authName);
        List<Role> roles = roleRepository.findAll();
        //如果删除被维护端的数据，则把用户（维护端）的List中要移除的角色（被维护端）都remove掉
        for (Role role : roles) {
            List<Authority> roleAuthoritis = role.getAuthorities();
            for (Authority authority : authorities) {
                if (roleAuthoritis.contains(authority)) {
                    roleAuthoritis.remove(authority);
                }
            }
            roleRepository.save(role);
        }
        authorityRepository.deleteByAuthorityName(authName);
    }
}
