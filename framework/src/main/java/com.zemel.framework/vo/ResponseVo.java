package com.zemel.framework.vo;

import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/4/4 23:40
 */
@Data
public class ResponseVo {
    private int code;
    private Object data;
    private String message;
    private static final int SUCCESS_CODE = 200;    // 数据成功
    private static final int TOKEN_FAIL = 201;      // token过期
    private static final int DATA_ERROR = 202;      // 操作失败
    private static final int EXCEPTION_TIP = 501;   // 异常提示
    private static final int EXCEPTION_CODE = 500;    // 异常
    public static final String a = "sfgd";

    private static ResponseVo build(int code, Object data, String message) {
        ResponseVo responseDto = new ResponseVo();
        responseDto.setCode(code);
        responseDto.setData(data);
        responseDto.setMessage(message);
        return responseDto;
    }

    public static ResponseVo buildSuccess(Object object, String message) {
        return build(SUCCESS_CODE, object, message);
    }

    public static ResponseVo buildFail(String message) {
        return build(TOKEN_FAIL, null, message);
    }

    public static ResponseVo buildFail(Object object, String message) {
        return build(TOKEN_FAIL, object, message);
    }

    public static ResponseVo buildNoData(String message) {
        return build(DATA_ERROR, null, message);
    }

    public static ResponseVo buildException(String message) {
        return build(EXCEPTION_CODE, null, message);
    }

    public static ResponseVo buildExceptionTip(String message) {
        return build(EXCEPTION_TIP, null, message);
    }

    public static ResponseVo build(boolean success) {
        if (success)
            return buildSuccess(null, "操作成功");
        return buildNoData("操作失败");
    }

    public static ResponseVo build(Object object) {
        if (object == null)
            return buildNoData("查询不到数据");
        if (object instanceof Boolean) {
            boolean success = (boolean) object;
            return build(success);
        }
        return buildSuccess(object, "获取数据成功");
    }


}
