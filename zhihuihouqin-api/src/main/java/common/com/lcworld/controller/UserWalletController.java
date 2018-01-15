package com.lcworld.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.consts.APPConstant;
import com.lcworld.dto.PurchaseDTO;
import com.lcworld.entity.DsfwCategoryEntity;
import com.lcworld.entity.LffwVoucherEntity;
import com.lcworld.entity.UserDepositEntity;
import com.lcworld.entity.UserWallelogEntity;
import com.lcworld.entity.UserWalleorderEntity;
import com.lcworld.entity.UserWalletEntity;
import com.lcworld.factory.OrderServiceFactory;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.BaseUserRoleService;
import com.lcworld.service.DsfwCategoryService;
import com.lcworld.service.LffwVoucherService;
import com.lcworld.service.PayinfoService;
import com.lcworld.service.PurchaseAccountService;
import com.lcworld.service.PurchaseTypeService;
import com.lcworld.service.UserCaptchaService;
import com.lcworld.service.UserDepositService;
import com.lcworld.service.UserWallelogService;
import com.lcworld.service.UserWalleorderService;
import com.lcworld.service.UserWalletService;
import com.lcworld.utils.DateUtil;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;
/**
 * 用户钱包表
 * @author leojr
 *
 */
@RestController
@RequestMapping("appuser/wallet")
public class UserWalletController {
    private Logger log = LoggerFactory.getLogger(UserWalletController.class);
    @Autowired
    private OrderServiceFactory orderServiceFactory;
    @Autowired
    private PayinfoService payinfoService;
    @Autowired
    private UserWalletService userWalletService;
    @Autowired
    private UserWalleorderService userWalleorderService;
    @Autowired
    private UserWallelogService userWallelogService;
    @Autowired
    private UserCaptchaService userCaptchaService;
    @Autowired
    private PurchaseTypeService purchaseTypeService;
    @Autowired
    private PurchaseAccountService purchaseAccountService;
    @Autowired
    private BaseUserRoleService baseUserRoleService;
    @Autowired
    private LffwVoucherService lffwVoucherService;
    @Autowired
    private UserDepositService userDepositService;
    @Autowired
    private DsfwCategoryService dsfwCategoryService;
    
    /**
     * 查询用户钱包信息
     * @param biz
     * @return
     */
    @RequestMapping("/findUserWallet")
    public R findUserWallet(HttpServletRequest req,String biz){
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        
        Long uid = (Long) req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        params.put("uid", uid);
        
        //用户钱包信息
        UserWalletEntity wallet = userWalletService.queryByUid(params.getInteger("uid"));
        if(wallet == null){
        	
            wallet = new UserWalletEntity();
            BigDecimal money = new BigDecimal(0);
            
            wallet.setUid(uid.intValue());
            wallet.setRemain(money);
            wallet.setPublicRemain(money);
            
			userWalletService.save(wallet);
        }
        
        //用户理发券
        LffwVoucherEntity voucher = lffwVoucherService.queryByUid(params.getInteger("uid"));
        
        HashMap<String,Object> result = new HashMap<>();
        result.put("walletRemain", wallet.getRemain());
        if (ValidateUtil.isValid(voucher)) {
        	result.put("voucherNum", voucher.getRemain());
		}else{
			result.put("voucherNum", 0);
		}
        result.put("payPasswordState", wallet.getPaypass()!=null?1:0);
        
        List<PurchaseDTO> pubPurchaseList = purchaseAccountService.getPubPurchaseList(params.getInteger("uid"), params.getString("serviceType"));
        BigDecimal publicRemain = new BigDecimal(0);
        if (ValidateUtil.isValid(pubPurchaseList)) {
        	for (PurchaseDTO p : pubPurchaseList) {
        		publicRemain = publicRemain.add(p.getMoney());
			}
		}
        result.put("publicRemain", publicRemain);
        result.put("hasBuyAuth", pubPurchaseList.size()==0?0:1);
        return R.ok(result);
    }
    /**
     * 验证旧密码
     * @param biz
     * @return
     */
    @RequestMapping("/checkOldPassword")
    public R checkOldPassword(HttpServletRequest req){
        String biz = req.getParameter("biz");
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        }else{
            //return R.error(1,"未登录");
        }
        //用户钱包信息
        UserWalletEntity wallet = userWalletService.queryByUid(params.getInteger("uid"));
        String oldPaypass = wallet.getPaypass();
        if (oldPaypass.equals(params.getString("paypass"))) {
			return R.ok();
		}else{
			return R.error(1,"密码错误");
		}
    }
    /**
     * 检测是否有支付密码
     * @param req
     * @return
     */
    @RequestMapping("/hasPaypass")
    public R hasPaypass(HttpServletRequest req){
    	String biz = req.getParameter("biz");
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        }else{
            //return R.error(1,"未登录");
        }
        //用户钱包信息
        UserWalletEntity wallet = userWalletService.queryByUid(params.getInteger("uid"));
        if (ValidateUtil.isValid(wallet.getPaypass())) {
			return R.ok();
		}else{
			return R.error(1,"没有支付密码");
		}
    }
    /**
     * 生成充值订单
     * @param req
     * @param biz={money,ordertype(订单类型19钱包20订水押金),paytype}
     * @return
     */
    @RequestMapping("/generateChargeWalletOrder")
    public R generateChargeWalletOrder(HttpServletRequest req,String biz){
        UserWalleorderEntity order = JSONObject.parseObject(biz, UserWalleorderEntity.class);
        order.setCreatetime(new Date());
        if (order.getOrdertype().intValue()==APPConstant.TYPE_DEPOSIT_DS) {// //订水押金
        	order.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_DEPOSIT_DS));
			
		}else if (order.getOrdertype().intValue()==APPConstant.TYPE_WALLET) {// 19钱包
			order.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_WALLET));
		}
        order.setStatus(APPConstant.TYPE_WALLET_ORDER_STATUS_ORDERED);
        order.setUid(Integer.parseInt(req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY).toString()));
        userWalleorderService.save(order);
        return R.ok().put("ordercode", order.getOrdercode()).put("orderid", order.getId()).put("ordertype", order.getOrdertype()).put("createtime", order.getCreatetime()).put("paytype", order.getPaytype()).put("totalMoney", order.getMoney());
    }
    /**
     * 查询钱包记录
     * @param req
     * @return
     */
    @RequestMapping("/findWalletLog")
    public R findWalletLog(HttpServletRequest req){
    	String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        }else{
            //return R.error(1,"未登录");
        }
        Integer usetype = params.getInteger("usetype");
        Query query = new Query(params);
		List<UserWallelogEntity> walletLogList = userWallelogService.queryList(query);
		int total = userWallelogService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(walletLogList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
    }
    /**
     * 查询充值记录
     * @param req
     * @return
     */
    @RequestMapping("/findChargeOrder")
    public R findChargeOrder(HttpServletRequest req){
    	String biz = req.getParameter("biz");
    	log.debug("biz:"+biz);
    	//查询列表数据
    	JSONObject params = JSONObject.parseObject(biz);
    	//添加uid到params
    	Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
    	if (ValidateUtil.isValid(uid)) {
    		params.put("uid", uid);
    	}else{
    		//return R.error(1,"未登录");
    	}
    	Query query = new Query(params);
    	List<UserWalleorderEntity> walletLogList = userWalleorderService.queryList(query);
    	//进行日期分组
    	List<Map<String,Object>> list = groupByDate(walletLogList);
    	return R.ok().put("list", list);
    }
    
	private List<Map<String, Object>> groupByDate(List<UserWalleorderEntity> walletLogList) {
    	Calendar c = Calendar.getInstance();
    	Calendar d = Calendar.getInstance();
    	d.setTime(new Date());
    	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
    	Integer preMonth = -1;
//    	Map<String,Object> map;
    	List<Map<String,Object>> list = new ArrayList<>();
    	for (UserWalleorderEntity order : walletLogList) {
    		Map<String,Object> innerMap = new HashMap<>();
    		Date createtime = order.getCreatetime();
    		c.setTime(createtime);
	    	Integer month = c.get(Calendar.MONTH)+1;
//	    	if (preMonth.intValue()!=month.intValue()) {
//	    		map = new HashMap<>();
//	    		innerList = new ArrayList<>();
	    		String yearMonth = "";
	    		int nowYear = d.get(Calendar.YEAR);
	    		int oldYear = c.get(Calendar.YEAR);
	    		if (nowYear==oldYear) {
					//今年
	    			if (d.get(Calendar.MONTH)==c.get(Calendar.MONTH)) {
						//本月
	    				yearMonth = "本月";
					}else{
						yearMonth = ""+(c.get(Calendar.MONTH)+1)+"月";
					}
				}else{
					yearMonth = ""+c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月";
				}
	    		innerMap.put("monthName", yearMonth);
//	    		map.put("monthList", innerList);
//	    		list.add(map);
//	    		//使相等
//	    		preMonth = month;
//	    	}
	    	
	    	innerMap.put("createtime", createtime);
	    	innerMap.put("weekday", getChineseWeek(DateUtil.getDayOfWeek(createtime)));
	    	innerMap.put("monthDay", sdf.format(createtime));
	    	innerMap.put("paytype", order.getPaytype());
	    	innerMap.put("money", order.getMoney());
	    	list.add(innerMap);
		}
		return list;
	}
	String getChineseWeek(int weekday){
    	switch (weekday) {
		case 1:return "周一";
		case 2:return "周二";
		case 3:return "周三";
		case 4:return "周四";
		case 5:return "周五";
		case 6:return "周六";
		case 7:return "周日";
		default: return "";
		}
    };
    public static void main(String[] args) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(new Date());
    	Integer month = c.get(Calendar.MONTH)+1;
		System.out.println(month);
	}
	/**
     * 支付密码修改
     * @param req
     * @return
     */
    @RequestMapping("/modifyCode")
    public R modifyCode(HttpServletRequest req){
    	String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        }else{
            //return R.error(1,"未登录");
        }
        //传入参数
        String code = params.getString("code");
        UserWalletEntity wallet = userWalletService.queryByUid(params.getInteger("uid"));
        wallet.setPaypass(code);
        userWalletService.update(wallet);
        return R.ok();
    }
    /**
     * 用户押金查询
     * @param req
     * @return
     */
    @RequestMapping("/getUserDeposit")
    public R getUserDeposit(HttpServletRequest req){
    	String biz = req.getParameter("biz");
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        }else{
            //return R.error(1,"未登录");
        }
        params.put("type", APPConstant.TYPE_DEPOSIT_DS);
        UserDepositEntity deposit = userDepositService.queryByCondition(params);
        if (ValidateUtil.isValid(deposit)) {
        	
		}else{
			deposit = new UserDepositEntity();
			deposit.setDeposit(new BigDecimal(0));
			deposit.setType(1);
		}
        //Integer id = params.getInteger("id");
        //查询用户选择的商品是否需要押金
        //DsfwCategoryEntity dsfwCategoryEntity = dsfwCategoryService.queryBygid(params);
        return R.ok().put("deposit", deposit); 
    }
    /**
     * 用户押金退还
     * @param req
     * @return
     */
    @RequestMapping("/backDeposit")
    public R backDeposit(HttpServletRequest req){
    	String biz = req.getParameter("biz");
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        }else{
            //return R.error(1,"未登录");
        }
        params.put("type", APPConstant.TYPE_DEPOSIT_DS);
        //
        R r = userDepositService.deleteUserDeposit(params);
        return r;
    }
    /**
     * 查询用户对公账户列表
     * @param req
     * @return
     */
    @RequestMapping("/findPurchaseAccountList")
    public R findPurchaseAccountList(HttpServletRequest req){
    	String biz = req.getParameter("biz");
    	//查询列表数据
    	JSONObject params = JSONObject.parseObject(biz);
    	//添加uid到params
    	Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
    	if (ValidateUtil.isValid(uid)) {
    		params.put("uid", uid);
    	}else{
    		//return R.error(1,"未登录");
    	}
    	List<PurchaseDTO> list = purchaseAccountService.getPubPurchaseList(params.getInteger("uid"), null);
    	return R.ok().put("accountList", list);
    }
}
