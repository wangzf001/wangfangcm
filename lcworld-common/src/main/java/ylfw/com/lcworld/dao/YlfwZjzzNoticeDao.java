package com.lcworld.dao;

import java.util.List;

import com.lcworld.entity.YlfwZjzzNoticeEntity;
import com.lcworld.utils.Query;

/**
 * 医疗服务专家坐诊-咨询
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public interface YlfwZjzzNoticeDao extends BaseDao<YlfwZjzzNoticeEntity> {

    List<YlfwZjzzNoticeEntity> queryNoticeList(Query q);

    int queryNoticeListTotal(Query q);
	
}
