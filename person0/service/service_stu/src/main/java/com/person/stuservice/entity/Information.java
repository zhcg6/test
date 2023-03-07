package com.person.stuservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lx
 * @since 2023-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Information对象", description="")
public class Information implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "手机号")
    private String telephone;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "地址")
    private String nowPlace;

    @ApiModelProperty(value = "专业")
    private String specialized;

    @ApiModelProperty(value = "每个月生活费")
    private Integer money;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "报到日期")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新信息时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "每周运动时间")
    private Double sportTime;

    @ApiModelProperty(value = "身高")
    private Double height;

    @ApiModelProperty(value = "睡眠时间")
    private Double sleep;

    @ApiModelProperty(value = "是否抽烟（1为抽烟，0位不抽烟）")
    private Boolean smoke;

    @ApiModelProperty(value = "学号")
    private String uno;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "体重")
    private Double weight;

    @ApiModelProperty(value = "逻辑删除（1为删除，0位不删除）")
    @TableLogic
    private Boolean isDelete;


}
