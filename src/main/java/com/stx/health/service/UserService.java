package com.stx.health.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stx.health.common.R;
import com.stx.health.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {

    public R<User> login(HttpServletRequest request, @RequestBody User user);

    public R<String> logout(HttpServletRequest request);

    public R<String> saveUser(HttpServletRequest request, @RequestBody User user);

    public R<String> saveAvatarByUserId(HttpServletRequest request, String avatar);

    public R<Page> UserQueryByUserName(int page, int pageSize, String userName);

}
