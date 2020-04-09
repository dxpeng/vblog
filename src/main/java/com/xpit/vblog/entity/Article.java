package com.xpit.vblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@ApiModel
public class Article {
    @ApiModelProperty(value = "文章id")
    private Long id;
    @ApiModelProperty(value = "文章标题")
    private String title;
    @ApiModelProperty(value = "md文件源码")
    private String mdContent;
    @ApiModelProperty(value = "html源码")
    private String htmlContent;
    @ApiModelProperty(value = "文章摘要")
    private String summary;
    @ApiModelProperty(value = "文章标签id")
    private Long cid;
    @ApiModelProperty(value = "文章作者")
    private Long uid;
    @ApiModelProperty(value = "发布日期")
    private Timestamp publishDate;
    @ApiModelProperty(value = "修改时间")
    private Timestamp editTime;
    @ApiModelProperty(value = "文章状态 0草稿 1已发布 2已删除")
    private Integer state;
    @ApiModelProperty(value = "文章浏览次数")
    private Integer pageView;
    @ApiModelProperty(value = "动态标签")
    private String[] dynamicTags;
    @ApiModelProperty(value = "作者昵称")
    private String nickname;
    @ApiModelProperty(value = "分类名称")
    private String cateName;
    @ApiModelProperty(value = "所属标签列表")
    private List<Tags> tags;
    @ApiModelProperty(value = "状态名称")
    private String stateStr;

    public Article() {
    }
}
