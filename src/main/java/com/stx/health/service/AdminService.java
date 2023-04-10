package com.stx.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stx.health.common.R;
import com.stx.health.pojo.Admin;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface AdminService extends IService<Admin> {

    public R<Admin> login(HttpServletRequest request, @RequestBody Admin admin);

    public R<String> logout(HttpServletRequest request);
}
