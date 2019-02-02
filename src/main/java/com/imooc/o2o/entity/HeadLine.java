package com.imooc.o2o.entity;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@RequiredArgsConstructor
public class HeadLine {
    /**
     * 头条id
     */
    private Long lineId;
    /**
     * 头条名称
     */
    private String lineName;
    /**
     * 头条链接
     */
    private String lineLink;
    /**
     * 链接照片
     */
    private String lineImg;
    /**
     * 链接等级
     */
    private Integer priority;
    /**
     * 是否可用
     */
    private Integer enableStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最近一次修改时间
     */
    private Date lastEditTime;


}
