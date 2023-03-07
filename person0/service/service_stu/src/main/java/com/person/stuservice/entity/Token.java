package com.person.stuservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("token")
@Data
public class Token {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String token;
}
