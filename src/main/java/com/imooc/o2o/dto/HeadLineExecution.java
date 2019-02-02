package com.imooc.o2o.dto;

import com.imooc.o2o.entity.HeadLine;
import com.imooc.o2o.enums.HeadLineStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class HeadLineExecution {
    private int state;

    private String stateInfo;

    private int count;

    //
    private HeadLine headLine;

    private List<HeadLine> headLineList;

    // 失败的构造器
    public HeadLineExecution(HeadLineStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 成功的构造器
    public HeadLineExecution(HeadLineStateEnum headLineStateEnum, HeadLine headLine) {
        this.state = headLineStateEnum.getState();
        this.stateInfo = headLineStateEnum.getStateInfo();
        this.headLine = headLine;
    }

    // 成功的构造器
    public HeadLineExecution(HeadLineStateEnum headLineStateEnum, List<HeadLine> headLineList) {
        this.state = headLineStateEnum.getState();
        this.stateInfo = headLineStateEnum.getStateInfo();
        this.headLineList = headLineList;
    }

}
