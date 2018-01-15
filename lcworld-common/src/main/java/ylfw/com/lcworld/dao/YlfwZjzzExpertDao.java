package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.dto.ZjzzExpertDTO;
import com.lcworld.entity.YlfwZjzzExpertEntity;
import com.lcworld.utils.Query;

/**
 * 医疗服务专家坐诊医生表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public interface YlfwZjzzExpertDao extends BaseDao<YlfwZjzzExpertEntity> {

    List<ZjzzExpertDTO> queryDoctorList(Query q);

    List<Map<String,Object>> queryexpertList(Query query);

    int queryexpertTotal(Query query);

	
}
