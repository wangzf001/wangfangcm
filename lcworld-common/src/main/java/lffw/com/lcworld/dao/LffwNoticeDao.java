package com.lcworld.dao;

import java.util.List;

import com.lcworld.entity.LffwNoticeEntity;
import com.lcworld.utils.Query;

/**
 * 理发服务-咨询
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
public interface LffwNoticeDao extends BaseDao<LffwNoticeEntity> {

    List<LffwNoticeEntity> querynoticeList(Query q);

    int querynoticeListTotal(Query q);
	
}
