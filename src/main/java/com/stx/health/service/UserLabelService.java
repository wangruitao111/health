package com.stx.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stx.health.common.R;
import com.stx.health.pojo.UserLabel;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface UserLabelService extends IService<UserLabel> {

    public R<String> saveUserLabel(String name);

    public R<String> updateUserLabelById(@RequestBody UserLabel userLabel);

    public R<String> removeUserLabelByUserId(Long id);

    public R<List<UserLabel>> getAllTheUserLabels();


}
