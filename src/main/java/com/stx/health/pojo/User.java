package com.stx.health.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    //  主键
    private Long id;
    // 姓名
    private String name;
    // 手机号
    private String phone;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 性别
    private String sex;
    // 身份证号
    private String idNumber;
    // 头像
    private String avatar;
    // 状态 0 禁用 | 1 正常
    private int status;
    // 是否注销 0 注销 | 1 正常
    private Integer isDeleted;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
