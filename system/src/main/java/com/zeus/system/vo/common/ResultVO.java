package com.zeus.system.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Controller 统一定义返回类
 *
 * @author zhuzihang
 */
@Data
@ApiModel(value = "统一返回包装类")
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 5610752011231336348L;

    /**
     * 操作成功
     */
    public static final ResultVO<String> SUCCESS = new ResultVO<>("200", "成功");
    /**
     * 操作失败
     */
    public static final ResultVO<String> FAIL = new ResultVO<>("0", "失败");
    /**
     * 数据不存在
     */
    public static final ResultVO<String> NULL = new ResultVO<>("-1", "数据不存在");
    /**
     * 系统发生异常
     */
    public static final ResultVO<String> EXCEPTION = new ResultVO<>("-2", "系统异常");
    /**
     * 参数错误
     */
    public static final ResultVO<String> PARAM_INVALID = new ResultVO<>("-3", "参数错误");
    /**
     * 校验异常
     */
    public static final ResultVO<String> ARGUMENT_ILLEGAL = new ResultVO<>("-4", "校验异常");
    /**
     * 接口调用异常
     */
    public static final ResultVO<String> FAIL_INVOKE_ID = new ResultVO<>("-5", "调用ID接口异常");
    /**
     * 没有权限
     */
    public static final ResultVO<String> FORBIDDEN = new ResultVO<>("403", "没有权限");
    /**
     * 数据重复存在
     */
    public static final ResultVO<String> EXIST = new ResultVO<>("40200", "数据已存在");
    /**
     *  不可更改
     */
    public static final ResultVO<String> CANNOT_CHANGED = new ResultVO<>("40201", "数据不可更改");

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private String code;


    /**
     * 返回信息
     */
    @ApiModelProperty(value = "返回信息")
    private String message;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据")
    private transient T data;

    /**
     * 默认构造，返回操作正确的返回代码和信息
     */
    public ResultVO() {
        this.setCode(SUCCESS.getCode());
        this.setMessage(SUCCESS.getMessage());
    }

    /**
     * 默认值返回，默认返回正确的code和message
     *
     * @param data
     */
    public ResultVO(T data) {
        ResultVO<String> v = data == null ? ResultVO.NULL : ResultVO.SUCCESS;
        this.setCode(v.getCode());
        this.setMessage(v.getMessage());
        this.setData(data);
    }

    /**
     * 构造一个返回特定代码的ResultVO对象
     *
     * @param code
     */
    public ResultVO(String code, String message) {
        super();
        this.setCode(code);
        this.setMessage(message);
    }

    /**
     * 构造自定义的code，message，以及data
     *
     * @param code
     * @param message
     * @param data
     */
    public ResultVO(String code, String message, T data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public String getCode() {
        return code;
    }

}