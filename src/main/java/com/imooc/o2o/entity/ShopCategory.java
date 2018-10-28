package com.imooc.o2o.entity;

import lombok.*;

import java.util.Date;

/**
 * @author Kimi Raikkonen
 *
 */

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@Builder
public class ShopCategory {

	private Long shopCategoryId;
	private String shopCategoryName;
	private String shopCategoryDesc;
	private String shopCategoryImg;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;
	private ShopCategory parent;
}
