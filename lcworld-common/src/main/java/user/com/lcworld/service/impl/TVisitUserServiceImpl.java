package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.RedisConst;
import com.lcworld.dao.TVisitUserDao;
import com.lcworld.entity.LffwBarberScheduleEntity;
import com.lcworld.entity.LffwCommentEntity;
import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.entity.TVisitUserEntity;
import com.lcworld.entity.WlryCommentEntity;
import com.lcworld.service.TVisitUserService;
import com.lcworld.service.WlryCommentService;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;



@Service("tVisitUserService")
public class TVisitUserServiceImpl implements TVisitUserService {
	@Autowired
	private TVisitUserDao tVisitUserDao;
	@Autowired
	private WlryCommentService wlryCommentService;
	
	@Override
	public TVisitUserEntity queryObject(Integer id){
		return tVisitUserDao.queryObject(id);
	}
	
	@Override
	public List<TVisitUserEntity> queryList(Map<String, Object> map){
		return tVisitUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tVisitUserDao.queryTotal(map);
	}
	
	@Override
	public void save(TVisitUserEntity tVisitUser){
		tVisitUserDao.save(tVisitUser);
	}
	
	@Override
	public void update(TVisitUserEntity tVisitUser){
		tVisitUserDao.update(tVisitUser);
	}
	
	@Override
	public void delete(Integer id){
		tVisitUserDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tVisitUserDao.deleteBatch(ids);
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
    public R cancelOrder(Integer id, String cancelreason) {
        TVisitUserEntity  order = queryObject(id);
        order.setCancelreason(cancelreason);
        order.setStatus(5);
        update(order);
        return R.ok();
    }

    @Override
    public void deleteOrder(Integer id) {
        TVisitUserEntity  order = queryObject(id);
        order.setIsdel(1);
        update(order);
        
    }

    @Override
    public R addComment(CommentVo commentvo, MultipartFile[] files) {
        WlryCommentEntity comment = new WlryCommentEntity();
        comment.setAnonymous(commentvo.getAnonymous());
        comment.setContent(commentvo.getContent());
        comment.setProductscore(commentvo.getProductscore());
        comment.setScore(commentvo.getScore());
        comment.setServicescore(commentvo.getServicescore());
        TVisitUserEntity order = queryObject(commentvo.getId());
        if(order.getStatus().intValue() ==4 ){
            JSONObject obj = WebUtils.uploadFiles(files, null, RedisConst.USER_YLFW_YYGH_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
            if (0 == obj.getIntValue("errorCode")) {
                comment.setImgs(obj.getString("data"));
            }
            comment.setCreatetime(new Date());
            comment.setUid(order.getUid());
            wlryCommentService.savecomment(comment,order);
            return R.ok();
        }else if(order.getStatus() == 3){
            return R.error(1103,"该订单已进行过评论,不可重复评论");
        }else{
            return R.error(1102, "该订单还没有完成，不能进行评价");
        }
    }

    @Override
    public R finishOrder(Integer id) {
        TVisitUserEntity order = queryObject(id);
        if(order.getStatus() != 3 && order.getStatus()  != 4){
            order.setStatus(3);
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
    public List<TVisitUserEntity> queryOrderList(Query q) {
       return tVisitUserDao.queryOrderList(q);
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
