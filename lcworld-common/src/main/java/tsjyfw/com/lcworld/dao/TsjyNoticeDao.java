package com.lcworld.dao;

import java.util.List;

import com.lcworld.entity.TsjyNoticeEntity;
import com.lcworld.utils.Query;

/**
 * 图书服务-咨询
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-24 10:06:29
 */
public interface TsjyNoticeDao extends BaseDao<TsjyNoticeEntity> {

    List<TsjyNoticeEntity> queryNoticeList(Query q);

    int queryNoticeListTotal(Query q);
	
}
