package com.person.stuservice.service.impl;

import com.person.stuservice.entity.User;
import com.person.stuservice.mapper.UserMapper;
import com.person.stuservice.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lx
 * @since 2023-02-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
