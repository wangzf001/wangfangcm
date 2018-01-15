package com.lcworld.utils;

public class JPushConst {
    
    /**
     * user alias规则 {%s:userid}
     */
    public static String JPUSH_USER_ALIAS = "jpush_user_%s";
    
    /**
     * server alias规则  [%s:ordertype，%s:userid]
     */
    public static String JPUSH_SERVER_ALIAS = "jpush_server_%s_%s";

    /**
     * 自定义参数key值
     */
    public static String CUSTOMER_PARAM = "zhihuihouqin_jpush_code";
    
    /**
     * 1000:网络报修，2000：医疗服务，3000：图书借阅，4000：餐费充值，5000：团队活动，6000：预约理发，7000:会议室预定,8000：办公用品，9000：订餐，10000：营养套餐，
     * 11000：信息公布，12000：意见反馈 ，13000：健康资讯，14000：专家坐诊  15000：工作餐 16000：干洗 17000 : 医疗服务预约挂号挂号单号, 18000:订水服务,19000:钱包,20000:订水押金
     * 编号开头规则
     * 末尾跟3位唯一数字
     */
    

    /*--------------------------------			报修维修		---------------------------------------*/
    
    /**
     * 下单通知
     */
    public static final String JPUSH_ACTIVITY_ORDER = "1000001";
    public static final String JPUSH_ACTIVITY_ORDER_ALERT = "有最新的报修订单,请前往查看";
    
    
}
