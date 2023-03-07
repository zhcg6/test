package com.person.stuservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.person.stuservice.entity.Zyh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ZyhMapper extends BaseMapper<Zyh> {
    void update(@Param("username") String username, @Param("password") String password);

    String select(String username);

    String selectId(String username);

    String getNumber(String username);

    void save(Zyh zyh);

    String getPhoneNumber(String phoneNumber);

    Zyh listUser(String username);

    String getUsername(String phoneNumber);


    String selectPermission(String username);

    void updateUser(Zyh zyh);
}
