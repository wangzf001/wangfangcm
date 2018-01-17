package com.lcworld.consts;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.lcworld.utils.wxutil.MD5Util;
import org.apache.commons.collections.bidimap.DualHashBidiMap;

/**
 * api常量
 * 
 * @ClassName: APIConstant
 * @Company: http://www.lcworld.cn/
 * @Description:
 * @author leojr
 * @date 2017年6月6日 下午3:46:52
 */

public class APPConstant {
    private static  Map<Integer,String> typeNameMap ;
    private static  Map<Integer, Integer> frontRoleMap ;
    private static  DualHashBidiMap ordertypeServiceMap ;
    private static  Map<Integer,String> PurchaseAccountNameMap ;
    private static  Map<String,Integer> purchaseTypeRightMap ;
    
    public static final String TYPE_BGYPFW_NAME = "purchaseid";
    public static final String TYPE_DSFW_NAME = "dspurchaseid";
        
	//1:网络报修，2：医疗服务，3：图书借阅，4：餐费充值，5：团队活动，6：预约理发，7会议室预定8：办公用品，9：订餐，10：营养套餐，
    //11：信息公布，12：意见反馈 ，13：健康资讯，14：专家坐诊  15：工作餐 16：干洗 17 : 医疗服务预约挂号挂号单号 18订水服务19钱包20订水押金
	public static final int TYPE_BXFW = 1;
	public static final int TYPE_YLFW = 2;
	public static final int TYPE_TSJYFW = 3;
	public static final int TYPE_CFCZ = 4;
	public static final int TYPE_TDHD = 5;
	public static final int TYPE_LFFW_YYLF = 6;
	public static final int TYPE_HYSFW = 7;
	public static final int TYPE_BGYPFW = 8;
	public static final int TYPE_DCFW = 9;
	public static final int TYPE_YYTC = 10;
    public static final int TYPE_XXGG = 11;
    public static final int TYPE_JYFK = 12;
    public static final int TYPE_JKZX = 13;
    public static final int TYPE_YLFW_ZJZZ = 14;
    public static final int TYPE_DCFW_GZC = 15;
    public static final int TYPE_GXDFW = 16;
    public static final int TYPE_YLFW_GH = 17;
    public static final int TYPE_DSFW = 18;
    public static final int TYPE_WALLET = 19;
    //订水押金
    public static final int TYPE_DEPOSIT_DS = 20;
    public static final int TYPE_VISIT_USER = 30;
    public static final int TYPE_WLRY = 21;
    
    public static final int TYPE_WALLET_ORDER_STATUS_ORDERED = 1;
    public static final int TYPE_WALLET_ORDER_STATUS_PAYED = 2;
    public static final int TYPE_WALLET_ORDER_STATUS_FAILED = 3;
    
    public static final int TYPE_CFCZ_ORDER_STATUS_ORDERED = 1;
    public static final int TYPE_CFCZ_ORDER_STATUS_PAYED = 2;
    public static final int TYPE_CFCZ_ORDER_STATUS_CHARGE_FAILED = 3;
    public static final int TYPE_CFCZ_ORDER_STATUS_CHARGE_SUCCESS = 4;
    
    public static final int TYPE_DCFW_GZC_NOFIXED = 1;
    public static final int TYPE_DCFW_GZC_FIXED = 2;

    public static final String APP_ACCESS_TOKEN = MD5Util.MD5Encode("zhihuihouqin", "UTF-8");
  //前端用户角色
    /**
     * 采购办公用品角色
     */
    public static final int USER_ROLEID_PURCHASE_BGYPFW = 1;
    
    /**
     * 采购订水角色
     */
    public static final int USER_ROLEID_PURCHASE_DSFW = 2;
    
    
    static {
        //name
        HashMap<Integer,String> typemap = new HashMap<Integer,String>();
        typemap.put(TYPE_BXFW,"网络报修");
        typemap.put(TYPE_TSJYFW,"图书借阅");
        typemap.put(TYPE_TDHD,"团队活动");
        typemap.put(TYPE_LFFW_YYLF,"预约理发");
        typemap.put(TYPE_BGYPFW,"办公用品");
        typemap.put(TYPE_DCFW,"订餐服务");
        typemap.put(TYPE_YYTC,"营养套餐");
        typemap.put(TYPE_XXGG,"信息公布");
        typemap.put(TYPE_JKZX,"健康资讯");
        typemap.put(TYPE_DCFW_GZC,"工作餐");
        typemap.put(TYPE_GXDFW,"干洗店服务");
        typemap.put(TYPE_YLFW_GH,"医疗服务预约挂号挂号单号");
        typemap.put(TYPE_DSFW,"订水服务");
        typemap.put(TYPE_DEPOSIT_DS,"订水押金");
        typemap.put(TYPE_VISIT_USER,"来访人员");
        APPConstant.typeNameMap = Collections.unmodifiableMap(typemap);
        
        //role
        HashMap<Integer,Integer> rolemap = new HashMap<Integer,Integer>();
        rolemap.put(TYPE_BGYPFW,USER_ROLEID_PURCHASE_BGYPFW);
        rolemap.put(TYPE_DSFW,USER_ROLEID_PURCHASE_DSFW);
        frontRoleMap = Collections.unmodifiableMap(rolemap);
        
        //oss
        DualHashBidiMap ordertypeServiceMap = new DualHashBidiMap();
        APPConstant.ordertypeServiceMap = ordertypeServiceMap;
        
        //purchaseAcount
        HashMap<Integer,String> namemap = new HashMap<Integer,String>();
        namemap.put(APPConstant.TYPE_BGYPFW,TYPE_BGYPFW_NAME);
        namemap.put(APPConstant.TYPE_DSFW,TYPE_DSFW_NAME);
        PurchaseAccountNameMap = Collections.unmodifiableMap(namemap);
        
        //purchaseTypeRight
        HashMap<String,Integer> purchasetypemap = new HashMap<String,Integer>();
        purchasetypemap.put("dsfw:firstadmin",TYPE_DSFW);
        purchasetypemap.put("dsfw:secondadmin",TYPE_DSFW);
        purchasetypemap.put("dsfw:thirdadmin",TYPE_DSFW);
        purchasetypemap.put("dcfw:thirdadmin",TYPE_DCFW_GZC);
        purchasetypemap.put("dcfw:thirdadmin",TYPE_DCFW_GZC);
        purchasetypemap.put("dcfw:thirdadmin",TYPE_DCFW_GZC);
        purchasetypemap.put("bgfw:thirdadmin",TYPE_BGYPFW);
        purchasetypemap.put("bgfw:thirdadmin",TYPE_BGYPFW);
        purchasetypemap.put("bgfw:thirdadmin",TYPE_BGYPFW);
        purchaseTypeRightMap = Collections.unmodifiableMap(purchasetypemap);
    }
    
 
    static {
        
    }
    public static Map<Integer, String> getPurchaseAccountNameMap() {
        return PurchaseAccountNameMap;
    }
    public static Map<Integer, String> gettypeMap(){
        return typeNameMap;
    }
    
    public static DualHashBidiMap getOrdertypeServiceMap(){
    	return ordertypeServiceMap;
    }
    
    public static Map<Integer, Integer> getFrontRoleMap() {
        return frontRoleMap;
    }
    public static Map<String, Integer> getPurchaseTypeRightMap() {
        return purchaseTypeRightMap;
    }

    public static void main(String[] args) {
		System.out.println(captureName("**************************"));
	}
    public static String captureName(String name) {
    	        char[] cs=name.toCharArray();
    	        cs[0]+=32;
    	        return String.valueOf(cs);
    }
	//状态1有0无
	public static final int TYPE_STATUS_HAS = 1;
	public static final int TYPE_STATUS_NO = 0;
	// 营养套餐
	public static final int YYTC_MTYPE_BREAKFAST = 1;
	public static final int YYTC_MTYPE_LUNCH = 2;
	public static final int YYTC_MTYPE_DINNER = 3;
	// 订单状态
	public static final int TYPE_ORDER_STATUS_ORDERED = 1;
	public static final int TYPE_ORDER_STATUS_SERVING = 2;
	public static final int TYPE_ORDER_STATUS_FINISHED = 3;
	//待评
	public static final int TYPE_ORDER_STATUS_EVALUATED = 4;
	public static final int TYPE_ORDER_STATUS_CANCEL = 5;
	public static final int TYPE_ORDER_STATUS_INVALID = 6;
	// 干洗订单Log状态
	public static final int TYPE_GXDFW_LOG_STATUS_ORDERED = 1;
	public static final int TYPE_GXDFW_LOG_STATUS_WAITSEND = 2;
	public static final int TYPE_GXDFW_LOG_STATUS_WASHING = 3;
	public static final int TYPE_GXDFW_LOG_STATUS_FINISH = 4;
	public static final int TYPE_GXDFW_LOG_STATUS_TOKEN = 5;
	public static final int TYPE_GXDFW_LOG_STATUS_PAYED = 6;
	public static final int TYPE_GXDFW_LOG_STATUS_EVALUATED = 7;

	// 支付状态
	public static final int TYPE_ORDER_PAY_STATUS_PAYED = 1;
	public static final int TYPE_ORDER_PAY_STATUS_UNPAY = 0;
	// 15分钟过期时间
	public static final int TYPE_DCFW_EXPIRETIME = 5;
	// 办公用品申请商品：未审核
	public static final int TYPE_BGYPFW_AP_STATUS_UNREVIEWED = 1;
	// 办公用品申请商品：在审核中
	public static final int TYPE_BGYPFW_AP_STATUS_UNDERREVIEW = 2;
	//会议室审核
	public static final int TYPE_HYSFW_CHECKING = 1;
	public static final int TYPE_HYSFW_CHECK_SUCCESSED = 2;
	public static final int TYPE_HYSFW_CHECK_FAILED = 3;
	
	
	public static final int TYPE_BGYPFW_AP_STATUS_SUCCESSED = 3;
	public static final int TYPE_BGYPFW_AP_STATUS_FAILED = 4;
	public static final double TYPE_BGYPFW_GOOD_MINSCORE = 4D;
	public static final double TYPE_BGYPFW_MIDDLE_MINSCORE = 2D;
	// 办公用品上架
	public static final int TYPE_BGYPFW_ONSALE = 1;
	public static final int TYPE_BGYPFW_ONSALE_CHECKING = 3;
	public static final int TYPE_BGYPFW_ONSALE_CHECK_FAILED = 4;
	// 办公用品下架
	public static final int TYPE_BGYPFW_NOTSALE = 2;
	public static final int TYPE_BGYPFW_NOTSALE_CHECKING = 5;
	public static final int TYPE_BGYPFW_NOTSALE_CHECK_FAILED = 6;
	// 0:线下支付，1:支付宝，2：微信，3：个人余额对公，4：个人余额对私 ，5：集体余额对公
	public static final int TYPE_PAYTYPE_OFFLINE = 0;
	public static final int TYPE_PAYTYPE_ZFB = 1;
	public static final int TYPE_PAYTYPE_WX = 2;
	public static final int TYPE_PAYTYPE_GRYE_G = 3;
	public static final int TYPE_PAYTYPE_GRYE_S = 4;
	public static final int TYPE_PAYTYPE_JTYE_G = 5;
	public static final int ADDRESS_DEFAULT = 1;
	public static final int ADDRESS_NOTDEFAULT = 0;
	public static final int TYPE_USER_USER = 1;
	public static final int TYPE_USER_SERVICE = 2;
	public static final int TYPE_HYSFW_APPOINTMENT_AVAILABLE = 1;
	public static final int TYPE_HYSFW_APPOINTMENT_UNAVAILABLE = 3;
	public static final int TYPE_HYSFW_APPOINTMENT_SELF = 2;
	//收藏服务
	public static final int FAVOR_TYPE_XXGG = 1;
	public static final int FAVOR_TYPE_BGYP = 2;
	public static final int FAVOR_TYPE_LFZP = 3;
	public static final int FAVOR_TYPE_TS = 4;
	public static final int FAVOR_TYPE_ZJ = 5;
	public static final int FAVOR_TYPE_HD = 6;
	public static final int FAVOR_TYPE_FXS = 7;
//	public static final int FAVOR_TYPE_JKZX = 7;
//	public static final int FAVOR_TYPE_HD = 8;
//	public static final int FAVOR_TYPE_HD = 9;
//	public static final int FAVOR_TYPE_HD = 10;
//	public static final int FAVOR_TYPE_HD = 11;
	public static final int TYPE_DELIVERY_RECEIVED = 1;
	public static final int TYPE_DELIVERY_SENDING = 2;
	public static final int TYPE_DELIVERY_SENDED = 3;
}
