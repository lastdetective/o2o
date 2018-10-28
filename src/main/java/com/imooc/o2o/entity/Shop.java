package com.imooc.o2o.entity;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@Builder
public class Shop {

	private Long shopId;
	private String shopName;
	private String shopDesc;
	private String shopAddr;
	private String phone;
	private String shopImg;
	private Double longitude;
	private Double latitude;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;
	private Integer enableStatus;
	private String advice;

	private Area area;
	private PersonInfo owner;
	private ShopCategory shopCategory;


	public String toString() {
		return "[shopId=" + shopId + ", shopName=" + shopName + "]";
	}


}
