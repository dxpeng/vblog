package com.xpit.vblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel
public class Category {
    @ApiModelProperty(value = "分类id")
    private Long id;
    @ApiModelProperty(value = "分类名称")
    private String cateName;
    @ApiModelProperty(value = "分类创建时间")
    private Timestamp date;

    public Category() {
    }

}
