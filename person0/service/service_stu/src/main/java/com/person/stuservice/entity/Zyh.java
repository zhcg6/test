package com.person.stuservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@ApiModel
@TableName("zyh")
@Data
public class Zyh {//总用户
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String username;
    private String password;
    private Integer age;
    private String sex;
    private String address;
    private String phonenumber;
    private String permission;
}
