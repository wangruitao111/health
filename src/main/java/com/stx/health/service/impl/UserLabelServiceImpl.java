package com.stx.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stx.health.common.BaseContext;
import com.stx.health.common.R;
import com.stx.health.mapper.UserLabelMapper;
import com.stx.health.pojo.UserLabel;
import com.stx.health.service.UserLabelService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserLabelServiceImpl extends ServiceImpl<UserLabelMapper, UserLabel> implements UserLabelService {

    @Override
    public R<String> saveUserLabel(String name) {
        UserLabel userLabel = new UserLabel();
        userLabel.setName(name);
        this.save(userLabel);
        return R.success("保存成功");
    }

    @Override
    public R<String> updateUserLabelById(@RequestBody UserLabel userLabel) {
        this.updateById(userLabel);
        return R.success("修改成功");
    }

    @Override
    public R<String> removeUserLabelByUserId(Long id) {

        LambdaQueryWrapper<UserLabel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserLabel::getId, id);
        this.remove(queryWrapper);
        return R.success("删除成功");
    }

    @Override
    public R<List<UserLabel>> getAllTheUserLabels() {
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<UserLabel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserLabel::getCreateUser, userId);
        List<UserLabel> userLabels = this.list(queryWrapper);
        return R.success(userLabels);
    }

}
