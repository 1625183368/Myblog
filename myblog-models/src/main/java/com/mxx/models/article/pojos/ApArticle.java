package com.mxx.models.article.pojos;

import lombok.Data;

import java.util.Date;

/**
 * @Param
 * id 主键
 * title 文章标题
 * authorId 作者id
 * authorName 作者名
 * icon 作者头像
 * images 文章图片
 * tagId 频道id
 * tagName 频道名称
 * views 浏览次数
 */

@Data
public class ApArticle {
    private Integer id;
    private String title;
    private Long authorId;
    private String authorName;
    private String icon;
    private String images;
    private Long tagId;
    private String tagName;
    private int visits;
    private Date createdTime;
    private Date publishTime;
}
