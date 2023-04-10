package com.stx.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stx.health.common.R;
import com.stx.health.mapper.ConsultMapper;
import com.stx.health.pojo.Consult;
import com.stx.health.service.ConsultService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ConsultServiceImpl extends ServiceImpl<ConsultMapper, Consult> implements ConsultService {
    @Override
    public R<String> saveConsult(@RequestBody Consult consult) {
        this.save(consult);
        return R.success("保存成功");
    }

    @Override
    public R<List<Consult>> getConsult() {
        Page<Consult> pageInfo = new Page<>(1, 6);
        LambdaQueryWrapper<Consult> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Consult::getCreateTime);
        Page<Consult> page = this.page(pageInfo, queryWrapper);
        List<Consult> consultList = page.getRecords();
        return R.success(consultList);
    }

    @Override
    public R<Page<Consult>> getConsultByName(int page, int pageSize, String name) {
        Page<Consult> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Consult> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Consult::getCreateTime);
        Page<Consult> consultPage = this.page(pageInfo, queryWrapper);
        return R.success(consultPage);
    }

    @Override
    public R<String> updateConsult(@RequestBody Consult consult) {
        this.updateById(consult);
        return R.success("修改成功");
    }

    @Override
    public R<String> deleted(@RequestParam List<Long> ids) {
        this.removeByIds(ids);
        return R.success("删除成功");
    }

}
