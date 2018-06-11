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
	private static final String REDIS_JKZX_BASE_URL = REDIS_BASE_URL+"jkzz:";
    /**
     * 允许图片
     */
    public static String[] UPLOAD_IMG_FILTER = {"GIF","PNG","JPG","JPEG"};
    /**
     * 验证码前缀
     */
    public static final  String USER_CAPTCHA_PRE = REDIS_USER_BASE_URL+"captcha:";
	public static final String USER_JKZZ_PRE = REDIS_JKZX_BASE_URL+"img";
	public static final String USER_PRE = REDIS_USER_BASE_URL+"img";
    
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
        typeimgmap.put(APPConstant.TYPE_JKZX,USER_JKZZ_PRE);
        typeimgmap.put(TYPE_OTHERS,USER_TEST_IMG_PRE);
        typeimgmap.put(TYPE_USER,USER_PRE);
        typeimgmaps = Collections.unmodifiableMap(typeimgmap);
    }
    
    
    
}
