package com.stx.health.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.sql.Blob;
import java.time.LocalDateTime;

@Data
public class UserPost {
    private static final long serialVersionUID = 1L;
    // 主键
    private Long id;
    // 用户Id
    @TableField(fill =  FieldFill.INSERT)
    private Long createUser;
    // 动态文字内容
    private String text;
    // 动态图片内容
    private String picture;
    // 动态视频内容
    private String video;
    // 创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
    // 是否删除
    @TableLogic
    private Integer isDeleted;
}
