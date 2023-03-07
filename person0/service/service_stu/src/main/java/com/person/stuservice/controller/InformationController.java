package com.person.stuservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.person.commonutils.R;
import com.person.stuservice.entity.Information;
import com.person.stuservice.entity.vo.InformationQuery;
import com.person.stuservice.service.InformationService;
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
 * @since 2023-02-27
 */
@RestController
@RequestMapping("/stuservice/information")
@CrossOrigin
public class InformationController {
    @Autowired
    private InformationService informationService;

    @ApiOperation("添加学生信息")
    @PostMapping("/addInfo")
    public R addStu(@RequestBody Information information){
        boolean save = informationService.save(information);
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
                      @RequestBody(required = false) InformationQuery informationQuery){

        Page<Information> pageInfomation = new Page<>(page,limit);
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        String name = informationQuery.getName();
        String sex = informationQuery.getSex();
        String sp = informationQuery.getSp();
        String start = informationQuery.getStart();
        String end = informationQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(sex)){
            wrapper.eq("sex",sex);
        }
        if(!StringUtils.isEmpty(sp)){
            wrapper.like("specialized",sp);
        }
        if(!StringUtils.isEmpty(start)){
            wrapper.ge("gmt_create",start);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        wrapper.orderByAsc("gmt_create");
        informationService.page(pageInfomation,wrapper);
        long total = pageInfomation.getTotal();
        List<Information> records =pageInfomation.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("学生信息删除")
    @DeleteMapping("/{id}")
    public R deleteInfo(@ApiParam(name = "id",value = "学生ID",required = true)
                        @PathVariable String id){
        boolean remove = informationService.removeById(id);
        if(remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("学生信息修改")
    @PostMapping("/updateInfo")
    public R updateInfo(@RequestBody Information information){
        boolean update = informationService.updateById(information);
        if(update){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @ApiOperation("根据id查询学生信息")
    @GetMapping("/getStuById/{id}")
    public R getStuById(@PathVariable String id) {
        Information byId = informationService.getById(id);
        return R.ok().data("stuInfo",byId);
    }

    @ApiOperation("学号验证")
    @GetMapping("verify/{name}/{uno}")
    public R vertify(@PathVariable String name,@PathVariable String uno){
        boolean flag = false;
        List<Information> list = informationService.list(null);
        String id = "";
        for (int i = 0; i < list.size(); i++) {
            Information information = list.get(i);
            if (name.equals(information.getName())){
                if (uno.equals(information.getUno())){
                    flag = true;
                    id = information.getId();
                    return R.ok().data("id",id);
                }
            }
        }
        return R.ok().data("id",0);
    }
}

