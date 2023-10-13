package com.lzy.platform.mybatis.enums;


import lombok.Getter;
import lombok.Setter;

/**
 * 数据权限类型枚举类
 *
 * @author lizuoyang
 * @date 2023/07/17
 */
@Getter
public enum DataPermissionTypeEnum {

    /**
     * 只能查看本人
     */
    DATA_PERMISSION_SELF("1", "DATA_PERMISSION_SELF"),

    /**
     * 只能查看本组织
     */
    DATA_PERMISSION_ORG("2", "DATA_PERMISSION_ORG"),

    /**
     * 只能查看本组织及子组织
     */
    DATA_PERMISSION_ORG_AND_CHILD("3", "DATA_PERMISSION_ORG_AND_CHILD"),

    /**
     * 可以查看所有数据
     */
    DATA_PERMISSION_ALL("4", "DATA_PERMISSION_ALL"),

    /**
     * 自定义数据权限
     */
    DATA_PERMISSION_CUSTOM("5", "DATA_PERMISSION_CUSTOM");


    public String level;

    public String type;

    DataPermissionTypeEnum(String level, String type) {
        this.level = level;
        this.type = type;
    }

    public static String getType(String level) {
        DataPermissionTypeEnum[] typeEnums = values();
        for (DataPermissionTypeEnum dataPermissionTypeEnum : typeEnums) {
            if (dataPermissionTypeEnum.getLevel() == level) {
                return dataPermissionTypeEnum.getType();
            }
        }
        return null;
    }

    public static String getLevel(String type) {
        DataPermissionTypeEnum[] typeEnums = values();
        for (DataPermissionTypeEnum dataPermissionTypeEnum : typeEnums) {
            if (dataPermissionTypeEnum.getType().equals(type)) {
                return dataPermissionTypeEnum.getLevel();
            }
        }
        return null;
    }
}
