package com.stx.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stx.health.common.Contants;
import com.stx.health.common.R;
import com.stx.health.mapper.AdminMapper;
import com.stx.health.pojo.Admin;
import com.stx.health.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    public R<Admin> login(HttpServletRequest request, @RequestBody Admin admin){
        String password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());

        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdmin, admin.getAdmin());
        Admin admin1 = this.getOne(queryWrapper);

        if (admin1 == null || !password.equals(admin1.getPassword())) return R.error("登陆失败");

        request.getSession().setAttribute(Contants.SESSION_ADMIN, admin1.getId());

        return R.success(admin1);
    }

    @Override
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute(Contants.SESSION_ADMIN);
        return R.success("登出成功");
    }

}
