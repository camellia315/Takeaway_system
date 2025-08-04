package com.health.constant;

/**
 * 消息常量
 */
public final class MessageConstant {

    private MessageConstant() {
        // 私有构造函数，防止实例化
    }

    //--- 通用操作 ---
    public static final String QUERY_SUCCESS = "查询成功";
    public static final String QUERY_FAIL = "查询失败";
    public static final String ADD_SUCCESS = "新增成功";
    public static final String ADD_FAIL = "新增失败";
    public static final String EDIT_SUCCESS = "编辑成功";
    public static final String EDIT_FAIL = "编辑失败";
    public static final String DELETE_SUCCESS = "删除成功";
    public static final String DELETE_FAIL = "删除失败";
    public static final String UPLOAD_SUCCESS = "上传成功";
    public static final String UPLOAD_FAIL = "上传失败";

    //--- 检查项相关 ---
    public static final String ADD_CHECKITEM_SUCCESS = "新增检查项成功";
    public static final String EDIT_CHECKITEM_SUCCESS = "编辑检查项成功";
    public static final String DELETE_CHECKITEM_SUCCESS = "删除检查项成功";
    public static final String QUERY_CHECKITEM_SUCCESS = "查询检查项成功";

    //--- 检查组相关 ---
    public static final String ADD_CHECKGROUP_SUCCESS = "新增检查组成功";
    public static final String EDIT_CHECKGROUP_SUCCESS = "编辑检查组成功";
    public static final String DELETE_CHECKGROUP_SUCCESS = "删除检查组成功";
    public static final String QUERY_CHECKGROUP_SUCCESS = "查询检查组成功";

    //--- 套餐相关 ---
    public static final String ADD_SETMEAL_SUCCESS = "新增套餐成功";
    public static final String EDIT_SETMEAL_SUCCESS = "编辑套餐成功";
    public static final String DELETE_SETMEAL_SUCCESS = "删除套餐成功";
    public static final String QUERY_SETMEAL_SUCCESS = "查询套餐成功";
    public static final String PIC_UPLOAD_SUCCESS = "图片上传成功";
    public static final String PIC_UPLOAD_FAIL = "图片上传失败";

    //--- 用户与权限 ---
    public static final String LOGIN_SUCCESS = "登录成功";
    public static final String LOGOUT_SUCCESS = "退出成功";
    public static final String PASSWORD_ERROR = "密码错误";
    public static final String USERNAME_NOT_FOUND = "用户不存在";
    public static final String GET_USERNAME_SUCCESS = "获取当前登录用户名称成功";
    public static final String GET_MENU_SUCCESS = "获取当前登录用户菜单成功";
    public static final String NO_PERMISSION = "权限不足";

    //--- 业务规则 ---
    public static final String HAS_ASSOCIATED_DATA = "当前项被引用，不能删除";

    // 【新增】手机端验证码相关
    public static final String SEND_VALIDATECODE_SUCCESS = "验证码发送成功";
    public static final String SEND_VALIDATECODE_FAIL = "验证码发送失败";
    public static final String VALIDATE_CODE_ERROR = "验证码输入错误";
    public static final String TELEPHONE_VALIDATECODE_NOTNULL = "手机号和验证码都不能为空";

    //--- 预约设置相关 ---
    public static final String QUERY_ORDER_SETTING_SUCCESS = "查询预约设置成功";
    public static final String QUERY_ORDER_SETTING_FAIL = "查询预约设置失败";
    public static final String IMPORT_ORDER_SETTING_FAIL = "批量导入预约设置数据失败";
    public static final String IMPORT_ORDER_SETTING_SUCCESS = "批量导入预约设置数据成功";
    public static final String EDIT_ORDER_SETTING_SUCCESS = "修改预约设置成功";
    public static final String EDIT_ORDER_SETTING_FAIL = "修改预约设置失败";
}