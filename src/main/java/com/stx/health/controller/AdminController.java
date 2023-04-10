package com.stx.health.controller;

import com.stx.health.common.R;
import com.stx.health.pojo.Admin;
import com.stx.health.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 管理员登录
     * @param request
     * @param admin
     * @return
     */
    @PostMapping("/login")
    public R<Admin> login(HttpServletRequest request, @RequestBody Admin admin){
        return adminService.login(request, admin);
    }

    /**
     * 管理员登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        return adminService.logout(request);
    }
}
