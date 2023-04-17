package com.stx.health.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stx.health.common.R;
import com.stx.health.pojo.UserGroup;

import java.util.List;


public interface UserGroupService extends IService<UserGroup> {

    public R<String> saveUserGroup(UserGroup userGroup);

    public R<List<UserGroup>> selectUserPostPage(int size, int sizePage);

}
