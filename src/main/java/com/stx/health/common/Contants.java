package com.stx.health.common;

public class Contants {
    public static final int RETURN_OBJECT_CODE_SUCCESS = 1; // 成功
    public static final int RETURN_OBJECT_CODE_FALL = 0;    // 失败

    // 保存当前用户的key
    public static final String SESSION_USER = "user";
    public static final String SESSION_ADMIN = "admin";

    public static final int STATUS_NORMAL = 1;      // 账号状态：正常
    public static final int STATUS_DISABLED = 0;    // 账号状态：禁用

    public static final int STATUS_LOG_OUT = 0;     // 账号注销

    public static final int STATUS_USE = 1;         // 账号使用

    public static final String USER_STATUS_1 = "待确认";
    public static final String USER_STATUS_2 = "已确认";
    public static final String USER_STATUS_3 = "已拒绝";
}
