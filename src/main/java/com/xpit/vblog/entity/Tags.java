package com.xpit.vblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class Tags {
    @ApiModelProperty(value = "标签id")
    private Long id;
    @ApiModelProperty(value = "标签名称")
    private String tagName;

    public Tags() {
    }
}
