package com.stx.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stx.health.common.R;
import com.stx.health.mapper.UserGroupMapper;
import com.stx.health.pojo.UserGroup;
import com.stx.health.service.UserGroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup> implements UserGroupService {
    @Override
    public R<String> saveUserGroup(UserGroup userGroup) {
        this.save(userGroup);
        return R.success("发送成功");
    }

    @Override
    public R<List<UserGroup>> selectUserPostPage(int size, int sizePage) {
        Page<UserGroup> pageInfo = new Page<>(size, sizePage);
        LambdaQueryWrapper<UserGroup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(UserGroup::getStartTime);
        Page<UserGroup> groupPage = this.page(pageInfo, queryWrapper);

        List<UserGroup> records = groupPage.getRecords().stream().map((item) -> {
            ArrayList<Float> coordinate = new ArrayList<>();
            coordinate.add(item.getLatitude());
            coordinate.add(item.getLongitude());
            item.setCoordinate(coordinate);
            return item;
        }).collect(Collectors.toList());

        return R.success(records);
    }
}
