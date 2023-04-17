package com.stx.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stx.health.mapper.UserPostLikeMapper;
import com.stx.health.pojo.UserPostLike;
import com.stx.health.service.UserPostLikeService;
import org.springframework.stereotype.Service;

@Service
public class UserPostLikeServiceImpl extends ServiceImpl<UserPostLikeMapper, UserPostLike> implements UserPostLikeService {
}
