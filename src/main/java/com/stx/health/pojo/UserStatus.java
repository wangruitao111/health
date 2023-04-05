package com.stx.health.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    // 主键
    private Long id;
    // 用户1
    private Long user1;
    // 用户2
    private Long user2;
    // 用户状态
    private String status;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    // 是否删除 0 删除 | 1 正常
    @TableField(exist = false)
    private Integer isDeleted;

}
