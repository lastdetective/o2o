package com.imooc.o2o.entity;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@Builder
public class PersonInfo {
	private Long userId;
	private String name;
	private String profileImg;
	private String email;
	private String gender;
	private Integer enableStatus;
	private Integer userType;
	private Date createTime;
	private Date lastEditTime;


}
