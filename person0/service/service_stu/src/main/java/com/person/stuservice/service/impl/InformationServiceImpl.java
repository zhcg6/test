package com.person.stuservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.person.stuservice.entity.Information;
import com.person.stuservice.entity.vo.Sex;
import com.person.stuservice.mapper.InformationMapper;
import com.person.stuservice.service.InformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lx
 * @since 2023-02-27
 */
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService {

    @Autowired
    private InformationService informationService;
    @Override
    public List<Sex> getSexList() {
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.eq("sex","男");
        int count = baseMapper.selectCount(wrapper);
        ArrayList<Sex> finalList = new ArrayList<>();
        return null;
    }

    @Override
    public void getList() {

    }

}
