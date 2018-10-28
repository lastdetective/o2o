package com.imooc.o2o.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@Slf4j
public class Area {
	
	/**
	 * 这里使用引用类型 是不希望 给赋初值
	 * 
	 * 
	 */
	
	
	// ID
	private Integer areaId;

	// 名称
	private String areaName;

	// 权重
	private Integer priority;

	// 创建时间
	private Date createTime;

	// 更新时间
	private Date lastEditTime;



	
	
}
