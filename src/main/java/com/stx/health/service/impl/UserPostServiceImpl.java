package com.stx.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stx.health.common.R;
import com.stx.health.mapper.UserPostMapper;
import com.stx.health.pojo.UserPost;
import com.stx.health.service.UserPostService;
import org.springframework.stereotype.Service;

@Service
public class UserPostServiceImpl extends ServiceImpl<UserPostMapper, UserPost> implements UserPostService {
    @Override
    public R<String> saveUserPost(UserPost userPost) {
        this.save(userPost);
        return R.success("发送成功");
    }

    @Override
    public R<Page<UserPost>> selectUserPostPageById(int size, int sizePage, Long id) {
        Page<UserPost> pageInfo = new Page<>(size, sizePage);
        LambdaQueryWrapper<UserPost> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPost::getCreateUser, id)
                    .orderByDesc(UserPost::getCreateTime);
        Page<UserPost> page = this.page(pageInfo, queryWrapper);
        return R.success(page);
    }
}
