package com.lzy.platform.base.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Feign DTO传输时的公共部分
 *
 * @author 2428
 * @date 2022/06/22
 */
@Data
public class BaseDTO implements Serializable {

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private Long creator;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private Long operator;

    @ApiModelProperty(value = "1:删除 0:不删除")
    private Integer delFlag;

}
