package com.stx.health.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stx.health.common.R;
import com.stx.health.pojo.Consult;
import com.stx.health.service.ConsultService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/consult")
public class ConsultController {

    @Resource
    private ConsultService consultService;

    /**
     * 新建资讯
     * @param consult
     * @return
     */
    @PostMapping("/save")
    public R<String> save(@RequestBody Consult consult){return consultService.saveConsult(consult);}

    /**
     * 查询最新的几条资讯
     * @return
     */
    @GetMapping("/getLater")
    public R<List<Consult>> getLaterConsult(){return consultService.getConsult();}

    /**
     * 查询资讯
     * @return
     */
    @GetMapping("/getPage")
    public R<Page<Consult>> getAllConsult(int page, int pageSize, String name){return consultService.getConsultByName(page, pageSize, name);}

    /**
     * 修改资讯
     * @param consult
     * @return
     */
    @PostMapping("/update")
    public R<String> update(@RequestBody Consult consult){return consultService.updateConsult(consult);}

    /**
     * 删除资讯
     * @param ids
     * @return
     */
    @DeleteMapping("/deleted")
    public R<String> deleted(@RequestParam List<Long> ids){return consultService.deleted(ids);}
}
