package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TsjyfwBookCopyEntity;
import com.lcworld.entity.TsjyfwBookEntity;
import com.lcworld.utils.Query;

/**
 * 图书借阅服务-图书
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public interface TsjyfwBookDao extends BaseDao<TsjyfwBookEntity> {

    List<com.lcworld.dto.TsjyfwBookDTO> queryBookList(Query query);

    void saveaAddOrderCountBench(List<String> ids);

    List<TsjyfwBookCopyEntity> queryUnBorrowCopyList(JSONObject obj);

    List<TsjyfwBookEntity> queryUnBorrowBookList(JSONObject obj);
	
}
