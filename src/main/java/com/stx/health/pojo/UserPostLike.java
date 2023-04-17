package com.stx.health.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPostLike {
    private static final long serialVersionUID = 1L;
    // 主键
    private Long id;
    // 用户id
    private Long userId;
    // 动态id
    private Long postId;
    // 创建时间
    private LocalDateTime createTime;
}
