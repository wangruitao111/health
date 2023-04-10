package com.stx.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stx.health.common.BaseContext;
import com.stx.health.common.Contants;
import com.stx.health.common.R;
import com.stx.health.mapper.UserMapper;
import com.stx.health.pojo.User;
import com.stx.health.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;


@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public R<User> login(HttpServletRequest request, @RequestBody User user) {
        String password = user.getPassword();
        // md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User u = this.getOne(queryWrapper);

        if (u == null || !u.getPassword().equals(password)) return R.error("登陆失败");
        if (u.getStatus() == Contants.STATUS_DISABLED) return R.error("账户已禁用");
        if (u.getIsDeleted() == Contants.STATUS_LOG_OUT) return R.error("账号已注销");

        request.getSession().setAttribute(Contants.SESSION_USER, u.getId());

        log.info( "{}" ,request.getSession().getAttribute(Contants.SESSION_USER));

        return R.success(u);
    }

    @Override
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute(Contants.SESSION_USER);
        return R.success("退出成功");
    }

    @Override
    public R<String> saveUser(HttpServletRequest request, @RequestBody User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User u = this.getOne(queryWrapper);
        if (u != null) return R.error("用户名已存在");
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        this.save(user);
        return R.success("注册成功");
    }

    @Override
    public R<String> saveAvatarByUserId(HttpServletRequest request, @RequestBody User user) {
        this.updateById(user);
        return R.success("保存成功");
    }

    @Override
    public R<User> selectUserById() {
        return R.success(this.getById(BaseContext.getCurrentId()));
    }

    @Override
    public R<Page> UserQueryByUserName(int page, int pageSize, String userName) {
        Page<User> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(userName), User::getUsername, userName.trim());
        queryWrapper.orderByDesc(User::getCreateTime);
        this.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

}
