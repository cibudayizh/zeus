package com.zeus.system.dto.common;

import lombok.Data;

/**
 * token中存放的用户信息
 * @author zhuzihang
 * @createTime 2023-02-09 009 11:49
 * @description
 */
@Data
public class UserInformationDto {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 租户id
     */
    private Long tenantId;
}
