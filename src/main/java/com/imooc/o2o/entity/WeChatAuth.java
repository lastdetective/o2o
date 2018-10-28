package com.imooc.o2o.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class WeChatAuth {
	private Long wechatAuthId;
	private String openId;
	private Date createTime;
	private PersonInfo personInfo;

	

}
