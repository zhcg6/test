package com.person.stuservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.person.stuservice.entity.Token;
import com.person.stuservice.entity.Zyh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TokenMapper extends BaseMapper<Token>{
    void saveToken(@Param("username") String username,@Param("uuid") String uuid);
}
