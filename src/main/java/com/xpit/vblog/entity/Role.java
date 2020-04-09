package com.xpit.vblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class Role {
    @ApiModelProperty(value = "角色id")
    private Long id;
    @ApiModelProperty(value = "角色名称")
    private String name;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
