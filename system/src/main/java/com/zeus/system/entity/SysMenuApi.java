package com.zeus.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sys_menu_api
 */
@TableName(value ="sys_menu_api")
@Data
public class SysMenuApi implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 权限编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 接口支持的Method，多个用英文逗号分隔
     */
    @TableField(value = "methods")
    private String methods;

    /**
     * 协议 http、websocket 等，默认为 http
     */
    @TableField(value = "scheme")
    private String scheme;

    /**
     * 是否支持 multipart/form-data 1=是，0=否
     */
    @TableField(value = "multipart_form_data")
    private Integer multipartFormData;

    /**
     * 是否记录标签日志 0否 1是
     */
    @TableField(value = "log_flag")
    private Integer logFlag;

    /**
     * 日志是否隐藏 0否 1是
     */
    @TableField(value = "log_hide_flag")
    private Integer logHideFlag;

    /**
     * 日志标签
     */
    @TableField(value = "tag")
    private String tag;

    /**
     * 服务名称
     */
    @TableField(value = "service_name")
    private String serviceName;

    /**
     * 内部路径
     */
    @TableField(value = "inner_url")
    private String innerUrl;

    /**
     * 外部url前缀
     */
    @TableField(value = "outer_url_prefix")
    private String outerUrlPrefix;

    /**
     * 外部url
     */
    @TableField(value = "outer_url")
    private String outerUrl;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}