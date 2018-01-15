package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.dto.ServiceUserDTO;
import com.lcworld.entity.DeliverymanEntity;
import com.lcworld.utils.Query;

/**
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-11 15:13:39
 */
public interface DeliverymanDao extends BaseDao<DeliverymanEntity> {

	DeliverymanEntity queryByCondition(Map<String, Object> params);

	List<DeliverymanEntity> queryDeliveryList(Map<String, Object> map);

	void updatePasswordByMobileAndServiceTypeId(Map<String, Object> params);

	int queryByPhoneAndServiceType(ServiceUserDTO user);

	DeliverymanEntity wzbqueryByCondition(Map<String, Object> params);
	
	List<?> queryListByScoretype(Query q);

    int queryCommentTotal(Query query);	
}
