package com.person.stuservice.service;

import com.person.stuservice.entity.Information;
import com.baomidou.mybatisplus.extension.service.IService;
import com.person.stuservice.entity.vo.Sex;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lx
 * @since 2023-02-27
 */
public interface InformationService extends IService<Information> {

    List<Sex> getSexList();

    void getList();
}
