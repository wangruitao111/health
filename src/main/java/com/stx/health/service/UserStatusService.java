package com.stx.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stx.health.common.R;
import com.stx.health.pojo.UserStatus;

import java.util.List;

public interface UserStatusService extends IService<UserStatus> {
    public R<String> addStatus(Long user1Id, Long user2Id);

    public R<List<UserStatus>> getUserStatus2ByUserId();

    public R<List<UserStatus>> getUserStatus13ByUserId();

    public R<String> removeUserStatusByTowUserId(Long userStatusId);
}
