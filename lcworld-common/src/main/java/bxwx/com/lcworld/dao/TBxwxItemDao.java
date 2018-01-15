package com.lcworld.dao;

import java.util.List;

import com.lcworld.dto.BxwxMenderItemDTO;
import com.lcworld.entity.TBxwxItemEntity;
import com.lcworld.utils.Query;

/**
 * 报修维修项
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
public interface TBxwxItemDao extends BaseDao<TBxwxItemEntity> {

    List<BxwxMenderItemDTO> queryItemList(Query q);
	
}
