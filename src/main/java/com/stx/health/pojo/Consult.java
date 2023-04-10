package com.stx.health.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 咨询
 */
@Data
public class Consult {
    // 主键
    private Long id;
    // 标题
    private String headLine;
    // 摘要
    private String digest;
    // 详情
    private String details;
    // 是否删除
    @TableField(exist = false)
    private int isDeleted;
    // 创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
    // 修改时间
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    // 创建人id
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long createUser;
    // 修改人id
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUser;
    // 图片
    private String picture;
}
