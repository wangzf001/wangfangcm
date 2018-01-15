package com.lcworld.consts;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RedisConst {
    //其他服务
    public static final  int TYPE_OTHERS = 10000;
    public static final  String REDIS_BASE_URL = "zhhq:";
    //用户的
    public static final  int  TYPE_USER = 9999;
    public static final String REDIS_USER_BASE_URL = REDIS_BASE_URL+"user:";
    public static final String REDIS_BXWX_BASE_URL = REDIS_BASE_URL+"bxwx:";
    public static final String REDIS_YLFW_YYGH_BASE_URL = REDIS_BASE_URL+"ylfw:yygh:";
    private static final String REDIS_LFFW_BASE_URL = REDIS_BASE_URL+"lffw:";
	public static final String REDIS_DCFW_GZC_BASE_URL = REDIS_BASE_URL+"dcfw:gzc:";
	public static final String REDIS_DCFW_BASE_URL = REDIS_BASE_URL+"dcfw:";
	private static final String REDIS_YYTCFW_BASE_URL = REDIS_BASE_URL+"yytcfw:";
	public static final String REDIS_DSFW_BASE_URL = REDIS_BASE_URL+"dsfw:";
	public static final String REDIS_BGYPFW_BASE_URL = REDIS_BASE_URL+"bgypfw:";
	private static final String REDIS_JYFW_BASE_URL = REDIS_BASE_URL+"jyfw:";
	private static final String REDIS_GXD_BASE_URL = REDIS_BASE_URL+"gxd:";
	private static final String REDIS_TDHD_BASE_URL = REDIS_BASE_URL+"tdhd:";
	private static final String REDIS_TSJY_BASE_URL = REDIS_BASE_URL+"tsjy:";
	private static final String REDIS_XXGB_BASE_URL = REDIS_BASE_URL+"xxgb:";
	private static final String REDIS_JKZX_BASE_URL = REDIS_BASE_URL+"jkzz:";
    /**
     * 允许图片
     */
    public static String[] UPLOAD_IMG_FILTER = {"GIF","PNG","JPG","JPEG"};
    /**
     * 验证码前缀
     */
    public static final  String USER_CAPTCHA_PRE = REDIS_USER_BASE_URL+"captcha:";
    /**
     * 报修维修订单图片
     */
    public static final  String USER_BXWX_ORDERIMG_PRE = REDIS_BXWX_BASE_URL+"img";
    /**
     * 医疗服务预约挂号订单图片
     */
    public static final  String USER_YLFW_YYGH_ORDERIMG_PRE = REDIS_YLFW_YYGH_BASE_URL+"img";
    /**
     * 订餐服务-工作餐评论图片
     */
    public static final  String USER_DCFW_GZC_ORDERIMG_PRE = REDIS_DCFW_GZC_BASE_URL+"img";
    public static final  String USER_DCFW_IMG_PRE = REDIS_DCFW_BASE_URL+"img";
    
	public static final String USER_DSFW_ORDERIMG_PRE = REDIS_DSFW_BASE_URL+"img";
	public static final String USER_BGYPFW_ORDERIMG_PRE = REDIS_BGYPFW_BASE_URL+"img";
	public static final String USER_YYTCFW_IMG_PRE = REDIS_YYTCFW_BASE_URL+"img";
	public static final String USER_JYFW_ORDERIMG_PRE = REDIS_JYFW_BASE_URL+"img";
	public static final String USER_GXD_ORDERIMG_PRE = REDIS_GXD_BASE_URL+"img";
	public static final String USER_TSJY_PRE = REDIS_TSJY_BASE_URL+"img";
	public static final String USER_XXGB_PRE = REDIS_XXGB_BASE_URL+"img";
	public static final String USER_JKZZ_PRE = REDIS_JKZX_BASE_URL+"img";
	public static final String USER_PRE = REDIS_USER_BASE_URL+"img";
    
    /**
     * 理发服务预约挂号订单图片
     */
    public static final String USER_LFFW_ORDERIMG_PRE = REDIS_LFFW_BASE_URL+"img";
    /**
     * 团队活动图片
     */
    public static final String REDIS_TDHD_IMG_PRE = REDIS_TDHD_BASE_URL+"img";
    /**
     * 测试
     */
    public static final String USER_TEST_IMG_PRE = "zhhq:test:"+"img";
    
    
    private static  Map<Integer,String> typeimgmaps ;
    public static Map<Integer,String> getTypeimgmaps(){
        return typeimgmaps;
    }
    static {
        HashMap<Integer,String> typeimgmap = new HashMap<Integer,String>();
        typeimgmap.put(APPConstant.TYPE_BXFW,REDIS_BXWX_BASE_URL);
        typeimgmap.put(APPConstant.TYPE_TSJYFW,USER_TSJY_PRE);
        typeimgmap.put(APPConstant.TYPE_TDHD,REDIS_TDHD_IMG_PRE);
        typeimgmap.put(APPConstant.TYPE_LFFW_YYLF,USER_LFFW_ORDERIMG_PRE);
        typeimgmap.put(APPConstant.TYPE_BGYPFW,USER_BGYPFW_ORDERIMG_PRE);
        typeimgmap.put(APPConstant.TYPE_DCFW,USER_DCFW_IMG_PRE);
        typeimgmap.put(APPConstant.TYPE_YYTC,USER_YYTCFW_IMG_PRE);
        typeimgmap.put(APPConstant.TYPE_XXGG,USER_XXGB_PRE);
        typeimgmap.put(APPConstant.TYPE_JKZX,USER_JKZZ_PRE);
        typeimgmap.put(APPConstant.TYPE_DCFW_GZC,USER_DCFW_GZC_ORDERIMG_PRE);
        typeimgmap.put(APPConstant.TYPE_GXDFW,USER_GXD_ORDERIMG_PRE);
        typeimgmap.put(APPConstant.TYPE_YLFW_GH,USER_YLFW_YYGH_ORDERIMG_PRE);
        typeimgmap.put(APPConstant.TYPE_DSFW,USER_DSFW_ORDERIMG_PRE);
        typeimgmap.put(APPConstant.TYPE_DSFW,USER_DSFW_ORDERIMG_PRE);
        typeimgmap.put(TYPE_OTHERS,USER_TEST_IMG_PRE);
        typeimgmap.put(TYPE_USER,USER_PRE);
        typeimgmaps = Collections.unmodifiableMap(typeimgmap);
    }
    
    
    
}
