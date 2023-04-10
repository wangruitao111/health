package com.stx.health.pojo;

import lombok.Data;

/**
 * 管理员
 */
@Data
public class Admin {
    // 主键
    private Long id;
    // 账号
    private String admin;
    // 密码
    private String password;
    // 手机号
    private String phone;
    // 电子邮件
    private String email;
    // 真实姓名
    private String name;
}
