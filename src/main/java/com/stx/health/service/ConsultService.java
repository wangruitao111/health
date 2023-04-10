package com.stx.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stx.health.common.R;
import com.stx.health.pojo.Consult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ConsultService extends IService<Consult> {

    public R<String> saveConsult(@RequestBody Consult consult);

    public R<List<Consult>> getConsult();

    public R<List<Consult>> getConsultByName(String name);

    public R<String> updateConsult(@RequestBody Consult consult);

    public R<String> deleted(@RequestParam List<Long> ids);

}
