package com.lcworld.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.dao.BgypfwCommentDao;
import com.lcworld.dao.BgypfwOrderDao;
import com.lcworld.dto.BxwxOrderDTO;
import com.lcworld.entity.BgypfwCommentEntity;
import com.lcworld.entity.BgypfwOrderEntity;
import com.lcworld.service.BgypfwCommentService;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.utils.WebUtils;



@Service("bgypfwCommentService")
public class BgypfwCommentServiceImpl implements BgypfwCommentService {
    @Autowired
    private BgypfwCommentDao bgypfwCommentDao;
    @Autowired
    private BgypfwOrderDao bgypfwOrderDao;
    
    @Override
    public BgypfwCommentEntity queryObject(Integer id){
        return bgypfwCommentDao.queryObject(id);
    }
    
    @Override
    public List<BgypfwCommentEntity> queryList(Map<String, Object> map){
        List<BgypfwCommentEntity> list = bgypfwCommentDao.queryList(map);
        for (int i = 0; i < list.size(); i++) {
            BgypfwCommentEntity comment = list.get(i);
            if (ValidateUtil.isValid(comment)) {
                String imgs = comment.getImgs();
                if (ValidateUtil.isValid(imgs)) {
                    String[] imgArr = imgs.split(",");
                    comment.getImgList().addAll(Arrays.asList(imgArr));
                }
            }else{
                list.remove(i);
            }
        }
        return list;
    }
    
    @Override
    public int queryTotal(Map<String, Object> map){
        return bgypfwCommentDao.queryTotal(map);
    }
    
    @Override
    public void save(BgypfwCommentEntity bgypfwComment){
        bgypfwCommentDao.save(bgypfwComment);
    }
    
    @Override
    public void update(BgypfwCommentEntity bgypfwComment){
        bgypfwCommentDao.update(bgypfwComment);
    }
    
    @Override
    public void delete(Integer id){
        bgypfwCommentDao.delete(id);
    }
    
    @Override
    public void deleteBatch(Integer[] ids){
        bgypfwCommentDao.deleteBatch(ids);
    }
    
    @Override
    public Double queryRateHighStars(Map<String, Object> map) {
        //设定分数最大最小值
        map.put("minScore", APPConstant.TYPE_BGYPFW_GOOD_MINSCORE);
//      map.put("maxScore", APPConstant.TYPE_BGYPFW_GOOD_MINSCORE);
        Integer count = bgypfwCommentDao.queryStarsCountWithin(map);
        int total = queryTotal(map);
        if (total==0) {
            return 0.0;
        }
        Double rate = count.intValue()/total*1.0;
        return rate;
    }

    @Override
    public R addComment(BgypfwCommentEntity commentEntity, MultipartFile[] imgFile) throws IOException {
    	JSONObject obj = WebUtils.uploadFiles(imgFile, null, RedisConst.USER_BGYPFW_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
		commentEntity.setImgs(obj.getString("data"));
        save(commentEntity);
        
        BgypfwOrderEntity orderEntity = new BgypfwOrderEntity();
        orderEntity.setId(commentEntity.getOrderid());
        orderEntity.setStatus(3);
        bgypfwOrderDao.update(orderEntity);
        return R.ok();
    }

	@Override
	public int queryStarsCountWithin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bgypfwCommentDao.queryStarsCountWithin(map);
	}

	@Override
	public List<?> OrderList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(String ordercode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BxwxOrderDTO orderDetail(String ordercode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void completeOrder(String biz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelorder(String ordercode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public R BeginService(String biz, String token) {
		// TODO Auto-generated method stub
		return null;
	}
}
