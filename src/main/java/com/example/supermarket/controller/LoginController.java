package com.example.supermarket.controller;

import com.example.supermarket.dao.UserRepository;
import com.example.supermarket.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private EntityService entityService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/login")
    public String login(){
        System.out.println("aaaa");
        return "login";
    }

    @RequestMapping("/")
    public String showHome(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登录用户: " + name);
        return "home";
    }

//    @ResponseBody
//    @PostMapping("/user/login")
//    public R login(@RequestParam("username")String username, @RequestParam("password")String password){
//        /**
//         * 根据数据库查询用户
//         */
//        System.out.println("aaaaa");
//        List<User> allUsers = userRepository.findAll();
//        Map<String, Object> map = new HashMap<>();
//        map.put("allUser", allUsers);
//        map.put("token", "111");
//        return R.ok().data(map);
//
//    }

    @RequestMapping("/admin")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String ShowAdmin(){
        return "你是管理者";
    }

    @RequestMapping("/user")
    @PreAuthorize("hasRole('ROLE_user')")
    public String ShowUser(){
        return "你是用户";
    }

}
