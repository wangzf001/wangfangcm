package com.lcworld.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.dao.TsjyfwBookCopyDao;
import com.lcworld.dao.TsjyfwBookDao;
import com.lcworld.dao.TsjyfwOrderDao;
import com.lcworld.dto.TsjyfwOrderDTO;
import com.lcworld.entity.TsjyfwBookCopyEntity;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.TsjyfwBookEntity;
import com.lcworld.entity.TsjyfwCommentEntity;
import com.lcworld.entity.TsjyfwOrderEntity;
import com.lcworld.service.TsjyfwBookCopyService;
import com.lcworld.service.TsjyfwCommentService;
import com.lcworld.service.TsjyfwOrderService;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.utils.WebUtils;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;
import com.lcworld.vo.TstjfwOrderVO;



@Service("tsjyfwOrderService")
public class TsjyfwOrderServiceImpl implements TsjyfwOrderService {
	@Autowired
	private TsjyfwOrderDao tsjyfwOrderDao;
	@Autowired
	private TsjyfwBookDao  tsjyfwBookDao;
	@Autowired
	private TsjyfwCommentService  tsjyfwCommentService;
	@Autowired
	private TsjyfwBookCopyService tsjyfwBookCopyService;
	
	@Override
	public TsjyfwOrderEntity queryObject(Integer id){
		return tsjyfwOrderDao.queryObject(id);
	}
	
	@Override
	public List<TsjyfwOrderEntity> queryList(Map<String, Object> map){
		return tsjyfwOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tsjyfwOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(TsjyfwOrderEntity tsjyfwOrder){
		tsjyfwOrderDao.save(tsjyfwOrder);
	}
	
	@Override
	public void update(TsjyfwOrderEntity tsjyfwOrder){
		tsjyfwOrderDao.update(tsjyfwOrder);
	}
	
	@Override
	public void delete(Integer id){
		tsjyfwOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tsjyfwOrderDao.deleteBatch(ids);
	}

	  @SuppressWarnings("unchecked")
    @Override
	    public JSONObject saveorder(TstjfwOrderVO ordervo) {
	      JSONObject result = new JSONObject();
	      result.put("errCode", 0);
	        if(ValidateUtil.isValid(ordervo.getBookids())){
	            List<TsjyfwOrderEntity> list = new ArrayList<TsjyfwOrderEntity>();
	            List<String> ids = Arrays.asList(ordervo.getBookids().split(","));
	            //checkAndGetIds(ids);
	            JSONObject jsonresult = check(ids);
	            if(0 != jsonresult.getIntValue("errCode")){
	                result.put("errCode", jsonresult.get("errCode"));
	                result.put("msg", jsonresult.getString("msg"));
	            }else{
	                for(String id :ids ){
	                    TsjyfwOrderEntity order = new TsjyfwOrderEntity();
	                    order.setBookid(Integer.parseInt(id));
	                    order.setCreatetime(new Date());
	                    order.setInvitegetbooktime(ordervo.getInvitegetbooktime());
	                    order.setUid(ordervo.getUid());
	                    order.setUmobile(ordervo.getUmobile());
	                    order.setUname(ordervo.getUname());
	                    order.setStatus(1);//已预订
	                    order.setIsdel(0);
	                    order.setChanges(1);
	                    try {
	                        Thread.sleep(1);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                    order.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_TSJYFW));
	                    list.add(order);
	                }
	                tsjyfwOrderDao.saveBatch(list);
	                //添加订单数
	                tsjyfwBookDao.saveaAddOrderCountBench(ids);
//	                tsjyfwBookCopyService.saveAddOrderCountBench();
	            }
	        }else{
	            result.put("errCode", 1400);
	            result.put("msg", "bookids 不为空");
	        }
	        return result;
	    }

    private JSONObject check(List<String> ids) {
        JSONObject obj = new JSONObject();
        obj.put("errCode", 0);
        obj.put("bookidlist", ids);
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        List<TsjyfwBookEntity> list = tsjyfwBookDao.queryUnBorrowBookList(obj);
        init(map ,ids);
        if(ValidateUtil.isValid(list)){
            for(TsjyfwBookEntity b : list){
                if(!map.get(b.getId().toString())){
                    map.put(b.getId().toString(), true);
                }
            }
            for(Entry<String, Boolean> entry : map.entrySet()){
                if(!entry.getValue()){
                    obj.put("errCode", 1420);
                    TsjyfwBookEntity book = tsjyfwBookDao.queryObject(entry.getKey());
                    String str="";
                    if(book != null){
                        str  = book.getTitle();
                    }
                    obj.put("msg", "图书："+str+"已借出，没有可借图书");
                    return obj;
                }
            }
            
        }else{
            obj.put("errCode", 1430);
            obj.put("msg", "没有可借图书");
        }
        return obj;
    }

//    private List<Integer> getids(List<TsjyfwBookCopyEntity> ids1) {
//        List<Integer> idlist = new ArrayList<Integer>();
//       for(TsjyfwBookCopyEntity c : ids1){
//           idlist.add(c.getId());
//       }
//       return idlist;
//    }

//    private JSONObject checkAndGetIds(List<String> ids) {
//        JSONObject obj = new JSONObject();
//        obj.put("errCode", 0);
//        obj.put("bookidlist", ids);
//        Map<String,Boolean> map = new HashMap<String,Boolean>();
//        init(map,ids);
//        List<TsjyfwBookCopyEntity> resultlist = new ArrayList<TsjyfwBookCopyEntity>();
//        List<TsjyfwBookCopyEntity> list = tsjyfwBookDao.queryUnBorrowCopyList(obj);
//        if(ValidateUtil.isValid(list)){
//            for(TsjyfwBookCopyEntity copy : list){
//                if(!map.get(copy.getBookid().toString())){
//                    map.put(copy.getBookid().toString(), true);
//                    resultlist.add(copy);
//                }
//            }
//            obj.put("ids", resultlist);
//            for(Entry<String, Boolean> entry : map.entrySet()){
//                if(!entry.getValue()){
//                    obj.put("errCode", 1420);
//                    TsjyfwBookEntity book = tsjyfwBookDao.queryObject(entry.getKey());
//                    obj.put("msg", "图书："+book.getTitle()+"已借出，没有可借图书");
//                    return obj;
//                }
//            }
//        }
//        
//        return obj;
//       
//    }

    private void init(Map<String, Boolean> map, List<String> ids) {
        for(String id : ids){
            map.put(id, false);
        }
        
    }

   
    @Override
    public List<TsjyfwOrderDTO> queryorderlist(Query q) {
        return tsjyfwOrderDao.queryorderlist(q);
    }

    @Override
    public List<Map<String,Object>> queryorderList(Query query) {
       return tsjyfwOrderDao.queryorderList(query);
    }

    @Override
    public int queryorderTotal(Query query) {
        return tsjyfwOrderDao.queryorderTotal(query);
    }

	@Override
	public PayOrderVo getOrderVo(PayOrderVo pay) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePayed(PayOrderVo orderVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public R cancelOrder(Integer id,String cancelreason) {
		TsjyfwOrderEntity order = queryObject(id);
		order.setChanges(1);
        order.setCancelreason(cancelreason);
        order.setStatus(5);
        update(order);
        return R.ok();
	}

	@Override
	public void deleteOrder(Integer id) {
		TsjyfwOrderEntity order = new TsjyfwOrderEntity();
		order.setId(id);
		order.setChanges(0);
		order.setIsdel(1);
        update(order);
	}

	@Override
	public R addComment(CommentVo comment, MultipartFile[] files) {
		TsjyfwCommentEntity lffwCommentEntity = new TsjyfwCommentEntity();
		lffwCommentEntity.setContent(comment.getContent());
		lffwCommentEntity.setOrderid(comment.getId());
		lffwCommentEntity.setProductscore(comment.getProductscore());
		lffwCommentEntity.setScore(comment.getScore());
		lffwCommentEntity.setServicescore(comment.getServicescore());
		TsjyfwOrderEntity order = queryObject(comment.getId());
        if(order.getStatus().intValue() ==3 ){
            JSONObject obj = WebUtils.uploadFiles(files, null, RedisConst.USER_YLFW_YYGH_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
            if (0 == obj.getIntValue("errorCode")) {
            	lffwCommentEntity.setImgs(obj.getString("data"));
            }
            lffwCommentEntity.setCreatetime(new Date());
            lffwCommentEntity.setUid(order.getUid());
            tsjyfwCommentService.savecomment(lffwCommentEntity,order);
            return R.ok();
        }else if(order.getStatus() == 4){
            return R.error(1103,"该订单已进行过评论,不可重复评论");
        }else{
            return R.error(1102, "该订单还没有完成，不能进行评价");
        }
	}

    @Override
    public R finishOrder(Integer id) {
        TsjyfwOrderEntity order = queryObject(id);
        if(order.getStatus() != 3 && order.getStatus()  != 4){
            order.setStatus(3);
            order.setChanges(1);
            update(order);
        }
        return null;
        
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
	public void instantService(String ordercode,Integer uid) {
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
	public void refuseOrder(JSONObject p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOverOrder(JSONObject in) {
		// TODO Auto-generated method stub
		
	}
    
	
}
