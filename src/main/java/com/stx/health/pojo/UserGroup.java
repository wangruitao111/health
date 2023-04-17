package com.stx.health.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class UserGroup{
    // 主键
    private Long id;
    // 创建者
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    // 组团内容
    private String content;
    // 经度
    private Float latitude;
    // 纬度
    private Float longitude;
    // 数组，用来存储经度纬度返回给前端
    // 坐标
    @TableField(exist = false)
    private ArrayList<Float> coordinate;
    // 开始时间
    private LocalDateTime startTime;
    // 结束时间
    private LocalDateTime endTime;
}
