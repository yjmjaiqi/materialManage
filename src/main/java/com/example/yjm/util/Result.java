package com.example.yjm.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;
    public static <T> Result<T> ok(T data) {
        return new Result<>(200, "请求成功", data);
    }
    public static <T> Result<T> codeErr(T data) {
        return new Result(401, "邮箱验证失败", data);
    }
    public static <T> Result<T> loginFail(T data) {
        return new Result(402, "登录失败", data);
    }
    public static <T> Result<T> err(T data) {
        return new Result(404, "请求失败", data);
    }
    public static <T> Result<T> authority(T data) {
        return new Result(403, "权限不足", data);
    }
    public static <T> Result<T> phoneBad(T data) {
        return new Result(501, "IP或pwd错误，请输入最少11位数的账号，密码最少为6位！", data);
    }

    public static <T> Result<T> delIdErr(T data) {
        return new Result(502, "删除失败，参数错误或不存在", data);
    }

    public static <T> Result<T> phoneNull(T data) {
        return new Result<>(503, "phone或pwd不能为空", data);
    }

    public static <T> Result<T> regFail(T data) {
        return new Result(504, "注册失败", data);
    }

    public static <T> Result<T> intFail(T data) {
        return new Result(505, "IP已存在", data);
    }

    public static <T> Result<T> updateFail(T data) {
        return new Result(506, "更新失败", data);
    }

    public static <T> Result<T> pwdNull(T data) {
        return new Result(507, "请先设置支付密码", data);
    }

    public static <T> Result<T> queryFail(T data) {
        return new Result(507, "查询失败", data);
    }

    public static <T> Result<T> mdFail(T data) {
        return new Result(508, "余额不足", data);
    }

    public static <T> Result<T> Fail(T data) {
        return new Result(509, "操作失败", data);
    }

    public static <T> Result<T> parameterFail(T data) {
        return new Result(510, "参数错误", data);
    }

    public static <T> Result<T> formatError(T data) {
        return new Result(511, "格式错误", data);
    }
}

