package com.person.stuservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.person.commonutils.R;
import com.person.stuservice.entity.Zyh;
import com.person.stuservice.entity.vo.ZyhQuery;
import com.person.stuservice.service.Zyh1Service;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lx
 * @since 2023-03-04
 */
@RestController
@RequestMapping("/stuservice/zyh1")
@CrossOrigin
public class Zyh1Controller {
    @Autowired
    private Zyh1Service zyh1Service ;

    @ApiOperation("添加用户信息")
    @PostMapping("/addInfo")
    public R addStu(@RequestBody Zyh zyh){
        boolean save = zyh1Service.save(zyh);
        if(save){
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @ApiOperation("分页查询")
    @PostMapping("/pageInfo/{page}/{limit}")
    public R pageInfo(@ApiParam(name="page", value = "当前页码",required = true)
                      @PathVariable Long page,
                      @ApiParam(name = "limit",value = "每页记录数",required = true)
                      @PathVariable Long limit,
                      @ApiParam(name = "infoQuery",value = "查询对象")
                      @RequestBody(required = false) ZyhQuery zyhQuery){

        Page<Zyh> pageZyh = new Page<>(page,limit);
        QueryWrapper<Zyh> wrapper = new QueryWrapper<>();
        String name = zyhQuery.getName();
        String username = zyhQuery.getUsername();

        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(username)){
            wrapper.eq("username",username);
        }

        zyh1Service.page(pageZyh,wrapper);
        long total = pageZyh.getTotal();
        List<Zyh> records =pageZyh.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("用户信息删除")
    @DeleteMapping("/{id}")
    public R deleteInfo(@ApiParam(name = "id",value = "用户ID",required = true)
                        @PathVariable String id){
        boolean remove = zyh1Service.removeById(id);
        if(remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("用户信息修改")
    @PostMapping("/updateInfo")
    public R updateInfo(@RequestBody Zyh zyh){
        boolean update = zyh1Service.updateById(zyh);
        if(update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("根据id查询学生信息")
    @GetMapping("/getStuById/{id}")
    public R getStuById(@PathVariable String id) {
        Zyh byId = zyh1Service.getById(id);
        return R.ok().data("stuInfo",byId);
    }

}

