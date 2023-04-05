package com.stx.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stx.health.common.BaseContext;
import com.stx.health.common.Contants;
import com.stx.health.common.R;
import com.stx.health.mapper.UserStatusMapper;
import com.stx.health.pojo.UserStatus;
import com.stx.health.service.UserStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStatusServiceImpl extends ServiceImpl<UserStatusMapper, UserStatus> implements UserStatusService {
    @Override
    public R<String> addStatus(Long user1Id, Long user2Id) {
        LambdaQueryWrapper<UserStatus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserStatus::getUser1, user1Id)
                    .eq(UserStatus::getUser2, user2Id)
                    .or()
                    .eq(UserStatus::getUser1, user2Id)
                    .eq(UserStatus::getUser2, user1Id);
        UserStatus status = this.getOne(queryWrapper);
        if (status != null){
            if (status.getStatus().equals(Contants.USER_STATUS_1)) return R.error("等待对方同意该请求");
            if (status.getStatus().equals(Contants.USER_STATUS_2)) return R.error("该用户已经是好友了");
        }
        status.setUser1(user1Id);
        status.setUser2(user2Id);
        status.setStatus(Contants.USER_STATUS_1);
        return R.success("发送请求成功");
    }

    @Override
    public R<List<UserStatus>> getUserStatus2ByUserId() {
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<UserStatus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserStatus::getUser1, userId)
                    .or()
                    .eq(UserStatus::getUser2, userId)
                    .eq(UserStatus::getStatus, Contants.USER_STATUS_2);
        List<UserStatus> userStatusList = this.list(queryWrapper);
        return  R.success(userStatusList);
    }

    @Override
    public R<List<UserStatus>> getUserStatus13ByUserId() {
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<UserStatus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserStatus::getUser1, userId)
                    .or()
                    .eq(UserStatus::getUser2, userId)
                    .eq(UserStatus::getStatus, Contants.USER_STATUS_1)
                    .or()
                    .eq(UserStatus::getStatus, Contants.USER_STATUS_3);
        List<UserStatus> userStatusList = this.list(queryWrapper);
        return R.success(userStatusList);
    }

    public R<String> removeUserStatusByTowUserId(Long userStatusId){
        LambdaQueryWrapper<UserStatus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserStatus::getId, userStatusId);
        UserStatus userStatus = this.getOne(queryWrapper);
        if (userStatus == null){
            return R.error("你们没有关系，无法删除好友");
        } else if (userStatus.getStatus().equals(Contants.USER_STATUS_1)){
            return R.error("您还不是好友,无法删除好友");
        } else if (userStatus.getStatus().equals(Contants.USER_STATUS_3)) {
            return R.error("您已经删除该好友，无法继续删除");
        }else {
            this.remove(queryWrapper);
            return R.success("删除成功");
        }
    }
}
