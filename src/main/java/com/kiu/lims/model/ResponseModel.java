package com.kiu.lims.model;

import lombok.Data;

@Data
public class ResponseModel {
    private Integer code;

    private String message;

    private Object data;
}
