package com.example.supermarket.controller;


import com.example.supermarket.dao.AuthorityRepository;
import com.example.supermarket.dao.RoleRepository;
import com.example.supermarket.dao.UserRepository;
import com.example.supermarket.entity.Authority;
import com.example.supermarket.entity.Role;
import com.example.supermarket.entity.User;
import com.example.supermarket.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class EntityController {
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

    private EntityService entityService;
    @Autowired
    public void setEntityService(EntityService entityService){
        this.entityService = entityService;
    }

    @RequestMapping("/finduser")
    public List<User> findByName(@RequestParam(value = "userName") String userName) {
        return userRepository.findAll();
    }

    @RequestMapping("/findalluser")
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @RequestMapping("/adduser")
    public List<User> addUser(@RequestParam(value = "userName") String userName,
                              @RequestParam(value = "roleName") String roleName,
                              @RequestParam(value = "password") String password) {
        User user = new User();
        Role role = roleRepository.findAllByRoleName(roleName).get(0);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRoles(new ArrayList<>());
        user.getRoles().add(role);//给用户设置权限
        userRepository.save(user);
        return userRepository.findAll();
    }

    /*
        给用户添加角色
     */
    @RequestMapping("/adduserrole")
    public List<User> addUserRole(@RequestParam(value = "userName") String userName,
                                  @RequestParam(value = "roleName") String roleName) {
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findAllByRoleName(roleName).get(0);
        if (user.getRoles() == null) {
            user.setRoles(new ArrayList<>());
        }
        user.getRoles().add(role);//给用户设置权限
        userRepository.save(user);
        return userRepository.findAll();
    }

    @RequestMapping("/deleteuser")
    public List<User> deleteUser(
            @RequestParam(value = "userName") String userName) {
        entityService.deleteUser(userName);
        return userRepository.findAll();
    }

    /*
        查询用户权限
     */
    @RequestMapping("/getauth")
    public Set<Authority> getAuthority(
            @RequestParam(value = "userName") String userName) {
        Set<Authority> authoritieSet = new HashSet<>();
        User user = userRepository.findByUserName(userName);
        for(Role role : user.getRoles()){
            for(Authority authority : role.getAuthorities()) {
                authoritieSet.add(authority);
            }
        }
        return authoritieSet;
    }

    /*
        角色部分的增删查改
     */
    @RequestMapping("/findallrole")
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @RequestMapping("/addrole")
    public List<Role> addRole(
            @RequestParam(value = "roleName") String roleName,
            @RequestParam(value = "authName") String authName) {
        Role role = new Role();
        Authority authority = authorityRepository.findAllByAuthorityName(authName).get(0);
        role.setRoleName(roleName);
        role.setAuthorities(new ArrayList<>());
        role.getAuthorities().add(authority);
        roleRepository.save(role);
        return roleRepository.findAll();
    }

    /*
        给角色添加权限
     */
    @RequestMapping("/addroleauth")
    public List<Role> addRoleAuth(
            @RequestParam(value = "roleName") String roleName,
            @RequestParam(value = "authName") String authName) {
        Role role = roleRepository.findAllByRoleName(roleName).get(0);
        Authority authority = authorityRepository.findAllByAuthorityName(authName).get(0);
        if (role.getAuthorities() == null) {
            role.setAuthorities(new ArrayList<>());
        }
        role.getAuthorities().add(authority);
        roleRepository.save(role);
        return roleRepository.findAll();
    }

    @RequestMapping("/deleterole")
    public List<Role> deleteRole(
            @RequestParam(value = "roleName") String roleName) {
        entityService.deleteRole(roleName);
        return roleRepository.findAll();
    }

    /*
        权限部分的增删查改
     */
    @RequestMapping("/findallauth")
    public List<Authority> findAllAuthority() {
        return authorityRepository.findAll();
    }

    @RequestMapping("/addauth")
    public List<Authority> addAuthority(
            @RequestParam(value = "authName" ) String authName) {
        Authority authority = new Authority();
        authority.setAuthorityName(authName);
        authorityRepository.save(authority);
        return authorityRepository.findAll();
    }

    @RequestMapping("/deleteauth")
    public List<Authority> deletAuthority(
            @RequestParam(value = "authName") String authName) {
        entityService.deleteAuthority(authName);
        return authorityRepository.findAll();
    }
}
