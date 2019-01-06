package com.imooc.o2o.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;


@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ImageHolder {
    private String imageName;
    private InputStream image;

}
