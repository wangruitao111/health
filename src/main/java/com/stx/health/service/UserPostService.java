package com.stx.health.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stx.health.common.R;
import com.stx.health.pojo.UserPost;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserPostService extends IService<UserPost> {
    public R<String> saveUserPost(@RequestBody UserPost userPost);

    public R<Page<UserPost>> selectUserPostPageById(int size, int sizePage ,Long id);
}
