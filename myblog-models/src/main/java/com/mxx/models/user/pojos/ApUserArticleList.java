package com.mxx.models.user.pojos;

import lombok.Data;

import java.util.Date;

@Data
public class ApUserArticleList {

  private long id;
  private long userId;
  private long tagId;
  private long articleId;
  private long isShow;
  private Date recommendTime;
  private long isRead;

}
