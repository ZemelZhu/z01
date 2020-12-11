package com.zemel.mallserver.vo;

import com.zemel.mallserver.entiy.AdminUserInfo;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/4/4 23:47
 */
@Builder
@Data
public class AdminUserInfoVo {
    private int id;
    private String name;
    private String avatar;
    public static AdminUserInfoVo buildInfo(AdminUserInfo adminUserInfo) {
        return AdminUserInfoVo.builder().id(adminUserInfo.getId()).name(adminUserInfo.getName()).avatar(adminUserInfo.getAvatar()).build();
    }
}
