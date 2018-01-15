package com.lcworld.dao;

import java.util.List;

import com.lcworld.dto.LffwServiceItemDTO;
import com.lcworld.entity.LffwServiceitemtypeEntity;

/**
 * 理发服务-服务项目分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:13
 */
public interface LffwServiceitemtypeDao extends BaseDao<LffwServiceitemtypeEntity> {

    List<LffwServiceItemDTO> queryitemlist();
	
}
