package com.lcworld.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.DcfwGzcOrderDao;
import com.lcworld.entity.DcfwGzcCommentEntity;
import com.lcworld.entity.DcfwGzcOrderEntity;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.TUserEntity;
import com.lcworld.exception.ZHHQException;
import com.lcworld.service.DcfwGzcCommentService;
import com.lcworld.service.DcfwGzcOrderService;
import com.lcworld.service.ServiceService;
import com.lcworld.service.TUserService;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.POIUtil;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;



@Service("dcfwGzcOrderService")
public class DcfwGzcOrderServiceImpl implements DcfwGzcOrderService {
	private Logger log = LoggerFactory.getLogger(DcfwGzcOrderServiceImpl.class);
	@Autowired
	private DcfwGzcOrderDao dcfwGzcOrderDao;
	@Autowired
	private DcfwGzcCommentService dcfwGzcCommentService;
	@Autowired
    private TUserService tUserService;
	@Autowired
	private ServiceService serviceService;
	@Override
	public DcfwGzcOrderEntity queryObject(Integer id){
		return dcfwGzcOrderDao.queryObject(id);
	}
	
	@Override
	public List<DcfwGzcOrderEntity> queryList(Map<String, Object> map){
		return dcfwGzcOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dcfwGzcOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(DcfwGzcOrderEntity dcfwGzcOrder){
		dcfwGzcOrderDao.save(dcfwGzcOrder);
	}
	
	@Override
	public void update(DcfwGzcOrderEntity dcfwGzcOrder){
		dcfwGzcOrderDao.update(dcfwGzcOrder);
	}
	
	@Override
	public void delete(Integer id){
		dcfwGzcOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dcfwGzcOrderDao.deleteBatch(ids);
	}

	@Override
	public R generateOrder(JSONObject params) {
		//需要用到的参数
		Integer uid = params.getInteger("uid");
		Integer sendfoodtime = params.getInteger("sendfoodtimeId");// 时间id **作废**
		String mobile = params.getString("mobile");// 
		Integer count = params.getInteger("count");// 预定份数    固定餐人数
		Integer type = params.getInteger("type");// 订单类型1普通餐2固定餐
        Integer duration = params.getInteger("duration");// 固定餐时间1一周2两周3一个月   **作废**
        String fixedmealtype = params.getString("fixedmealtype");// 固定餐类型1午餐2晚餐，多选逗号分隔例如：1,2
        Integer paytype = params.getInteger("paytype");// 
        String fixedmealstartdate = params.getString("fixedmealstartdate");
        String fixedmealenddate = params.getString("fixedmealenddate");
        //TUserEntity queryObject = tUserService.queryObject(params.getInteger("uid"));
        
		//初始化参数
        ServiceEntity service = serviceService.queryService(APPConstant.TYPE_DCFW_GZC);
		BigDecimal price = new BigDecimal(0);
		BigDecimal sum = new BigDecimal(0);
		DcfwGzcOrderEntity orderEntity = new DcfwGzcOrderEntity();
		TUserEntity user = tUserService.queryObject(params.getInteger("uid"));
		orderEntity.setCreatetime(new Date());
		orderEntity.setSendfoodtime(sendfoodtime);
//		orderEntity.setMobile(user.getMobile());
		orderEntity.setMobile(mobile);
		orderEntity.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_DCFW_GZC));
		orderEntity.setRealname(user.getRealname());
		orderEntity.setStatus(APPConstant.TYPE_ORDER_STATUS_ORDERED);
		orderEntity.setUid(uid);
		orderEntity.setType(type);
		orderEntity.setPayStatus(APPConstant.TYPE_ORDER_PAY_STATUS_UNPAY);
		
		// 2固定餐
		if (type.intValue()==APPConstant.TYPE_DCFW_GZC_FIXED) {
			price = service.getDcfwgzcfixedprice();
			orderEntity.setFixedmealtype(fixedmealtype);
			
			Date fixedmealstartdateDate = DateUtils.parse(fixedmealstartdate, "yyyy-MM-dd");
			Date fixedmealenddateDate = DateUtils.parse(fixedmealenddate, "yyyy-MM-dd");
			
			// 固定餐开始时间
			orderEntity.setFixedmealstartdate(fixedmealstartdateDate);
			// 固定餐结束时间
			orderEntity.setFixedmealenddate(fixedmealenddateDate);
			
			// 类型数量
			int typecount = 1;
			if (ValidateUtil.isValid(fixedmealtype)) {
				String[] split = fixedmealtype.split(",");
				typecount = split.length;
			}
			
			// 间隔天数
			int day = DateUtils.getDaySub(fixedmealstartdateDate,fixedmealenddateDate);
			
			orderEntity.setCount(day*typecount*count);
			sum = price.multiply(new BigDecimal(day*typecount*count));
			orderEntity.setTotalprice(sum);
			orderEntity.setPrice(price);
		}else{
			price = service.getDcfwgzcprice();
			sum = price.multiply(new BigDecimal(count));
			orderEntity.setCount(count);
			orderEntity.setTotalprice(sum);
			orderEntity.setPrice(price);
		}
		dcfwGzcOrderDao.save(orderEntity);
		return R.ok().put("ordertype", APPConstant.TYPE_GXDFW).put("ordercode", orderEntity.getOrdercode()).put("createtime", new Date()).put("paytype",paytype).put("totalMoney", sum);
	}
	
	@Override
    public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
	    DcfwGzcOrderEntity order;
	    if(pay.getOrderid() != null&&pay.getOrderid().intValue()!=0){
            order = queryObject(pay.getOrderid());
        }else{
            JSONObject params = new JSONObject();
            params.put("payordercode", pay.getOrdercode());
            List<DcfwGzcOrderEntity> orderlist = queryList(params);
            if(ValidateUtil.isValid(orderlist)){
                order = orderlist.get(0);
            }else{
                throw new ZHHQException(1500, "支付前订单校验失败，可能原因:订单不存在");
            }
        }
        PayOrderVo payorder = new PayOrderVo();
        payorder.setUid(order.getUid());
        payorder.setOrdercode(order.getOrdercode());
        payorder.setOrderid(order.getId());
        payorder.setOrdertype(APPConstant.TYPE_DCFW_GZC);
        payorder.setPaymoney(order.getTotalprice());
        payorder.setStatus(order.getStatus());
        return payorder;
    }

    private boolean checkBeforePay(DcfwGzcOrderEntity order) {
        if(order == null || 1 == order.getPayStatus() ){
            return false;
        }
        return true;
    }
    

    @Override
    public void savePayed(PayOrderVo orderVo) {
    	Map<String,Object> params = new HashMap<>();
    	params.put("payordercode", orderVo.getOrdercode());
    	List<DcfwGzcOrderEntity> list = queryList(params);
    	if (ValidateUtil.isValid(list)) {
        	for (DcfwGzcOrderEntity order : list) {
    			order.setPayStatus(1);
    			order.setPayType(orderVo.getPaytype());
    			update(order);
        	}
		}
    }
    @Override
   	public int invalidOrderBatch(Integer[] array) {
   		HashMap<String,Object> params = new HashMap<>();
   		params.put("ids", array);
   		params.put("orderStatus", APPConstant.TYPE_ORDER_STATUS_INVALID);
   		params.put("orderStatusFormer", APPConstant.TYPE_ORDER_STATUS_CANCEL);
   		int num = dcfwGzcOrderDao.updateOrderBatch(params);
   		return num;
   	}

	@Override
	public R cancelOrder(Integer id, String cancelreason) {
		DcfwGzcOrderEntity order = queryObject(id);
		if (ValidateUtil.isValid(order)) {
			if (order.getType().intValue()==APPConstant.TYPE_DCFW_GZC_FIXED) {
				Calendar c1 = Calendar.getInstance();
				Calendar c2 = Calendar.getInstance();
				c1.setTime(new Date());
				c2.setTime(order.getFixedmealstartdate());
				int day = c1.get(Calendar.DATE)-c2.get(Calendar.DATE);
				int i = c1.get(Calendar.HOUR);
				String fixedmealtype = order.getFixedmealtype();
				int typemix = 0;
				if (ValidateUtil.isValid(fixedmealtype)) {
					String[] split = fixedmealtype.split(",");
					for (String string : split) {
						int type = Integer.parseInt(string);
						typemix += type;
					}
				}
				int count = 0;
				BigDecimal price = order.getPrice();
				switch (typemix) {
				case 1:
					//午餐
					if (i<10) {
						//10点前取消
						count  = day;
					}else{
						count  = day+1;
					}
					break;
				case 2:
					//晚餐
					if (i<16) {
						//16点前取消
						count  = day;
					}else{
						count  = day+1;
					}
					break;
				case 3:
					//午餐+晚餐
					if (i<10) {
						//16点前取消
						count  = day*2;
					}else if (i<16) {
						count  = day*2+1;
					}else{
						count  = day*2+2;
					}
					break;
				default:
					break;
				}
				BigDecimal sum = price.multiply(new BigDecimal(count));
				order.setCount(count);
				order.setTotalprice(sum);
				order.setStatus(APPConstant.TYPE_ORDER_STATUS_CANCEL);
				order.setPayStatus(APPConstant.TYPE_ORDER_PAY_STATUS_UNPAY);
			}else{
				// 如果是普通餐，并且订单状态不等于1: 已预约
				Integer status = order.getStatus();
				if (status!=APPConstant.TYPE_ORDER_STATUS_ORDERED) {
					return R.error(2,"非法操作");
				}
				order.setStatus(APPConstant.TYPE_ORDER_STATUS_CANCEL);
				order.setReasonContent(cancelreason);
			}
			update(order);
		}
		return R.ok();
	}

	@Override
	public void deleteOrder(Integer id) {
		Integer[] ids = {id};
		invalidOrderBatch(ids);
	}

	@Override
	public R addComment(CommentVo comment, MultipartFile[] files) {
		DcfwGzcCommentEntity commentEntity = new DcfwGzcCommentEntity();
		commentEntity.setAnonymous(comment.getAnonymous());
		commentEntity.setContent(comment.getContent());
		commentEntity.setCreatetime(new Date());
		commentEntity.setOrderid(comment.getId());
		commentEntity.setProductscore(comment.getProductscore());
		commentEntity.setScore(comment.getScore());
		commentEntity.setServicescore(comment.getServicescore());
		commentEntity.setUid(comment.getUid());
		try {
			return dcfwGzcCommentService.addComment(commentEntity, files);
		} catch (IOException e) {
			e.printStackTrace();
			return R.error(2,"添加评论失败");
		}
	}

	@Override
    public R finishOrder(Integer id) {
		DcfwGzcOrderEntity order = queryObject(id);
		if (order.getType().intValue()==APPConstant.TYPE_DCFW_GZC_NOFIXED) {
			//非固定餐
			if (order.getStatus()==APPConstant.TYPE_ORDER_STATUS_ORDERED) {
				order.setStatus(APPConstant.TYPE_ORDER_STATUS_FINISHED);
			}else{
				return R.error(1,"原始状态有误");
			}
			update(order);
		}else{
			return R.error(1,"固定餐");
		}
		return R.ok();
    }

	@Override
	public Date getDeliverytime(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R updateOrderStatus(Integer id, Integer lastStatus, Integer newStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBatch(Integer[] ids, int status) {
		HashMap<String,Object> params = new HashMap<>();
		params.put("orderStatus", status);
		params.put("ids", ids);
		params.put("orderStatusFormer", APPConstant.TYPE_ORDER_STATUS_ORDERED);
		dcfwGzcOrderDao.updateOrderBatch(params);
	}

	@Override
	public RefundinfoEntity getRefundInfo(RefundinfoEntity refundVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRefund(RefundinfoEntity refundVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<?> OrderList(Query q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T OrderDetail(JSONObject params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrders(JSONObject in) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishOrders(JSONObject p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void instantService(String ordercode, Integer uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public R cancelOrders(JSONObject in) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void distribution(JSONObject in) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportExcel(DualHashBidiMap titleMapping, List<DcfwGzcOrderEntity> objectList,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			POIUtil.generateExcel(titleMapping, objectList, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<DcfwGzcOrderEntity> importExcel(Class<DcfwGzcOrderEntity> t, DualHashBidiMap titleMapping,
			MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refuseOrder(JSONObject p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOverOrder(JSONObject in) {
		// TODO Auto-generated method stub
		
	}
}
