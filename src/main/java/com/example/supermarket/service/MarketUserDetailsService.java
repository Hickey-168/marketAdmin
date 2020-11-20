package com.example.supermarket.service;


import com.example.supermarket.dao.AuthorityRepository;
import com.example.supermarket.dao.UserRepository;
import com.example.supermarket.entity.Role;
import com.example.supermarket.entity.SercuriyUser;
import com.example.supermarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class MarketUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        System.out.println("loadByUsername has been invloked.");
        if (user == null){
            System.out.println("我用户呢？");
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("登录用户的用户ID是 : " + user.getUserId());
        List<Role> roles = roleService.listByUserId(user.getUserId());
        System.out.println(roles.isEmpty());
        for(Role userRole : roles){
            Role role = roleService.findRoleById(userRole.getRoleId());
            System.out.println("the role id is : " + userRole.getRoleId());
           roles.add(role);
        }

        return new SercuriyUser(user.getUserName(),user.getPassword(),user.getRoles());
    }
}
