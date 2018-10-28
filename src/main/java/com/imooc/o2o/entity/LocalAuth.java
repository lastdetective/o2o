package com.imooc.o2o.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class LocalAuth {
    private Long localAutjId;
    private String username;
    private String password;

    private Date createTime;
    private Date lastEditTime;

    private PersonInfo personInfo;


}
