package com.stx.health.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户标签
 */
@Data
public class UserLabel implements Serializable {
    private static final long serialVersionUID = 1L;
    // 用户标签主键
    private Long id;
    // 用户主键
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    // 标签名字
    private String name;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    // 更改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    // 是否删除 0 删除 | 1 正常
    @TableField(exist = false)
    private Integer isDeleted;
}
