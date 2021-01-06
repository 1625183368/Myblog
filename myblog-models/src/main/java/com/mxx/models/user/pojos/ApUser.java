package com.mxx.models.user.pojos;


import lombok.Data;

import java.util.Date;

@Data
public class ApUser {

  private long id;
  private String name;
  private String password;
  private String phone;
  private String image;
  private long sex;
  private long status;
  private Date createdTime;



}
